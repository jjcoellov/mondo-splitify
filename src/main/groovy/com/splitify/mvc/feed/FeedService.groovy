package com.splitify.mvc.feed

import com.splitify.mvc.client.MondoAPIClient
import com.splitify.mvc.friends.Friend
import com.splitify.mvc.friends.FriendsRepository
import com.splitify.mvc.transaction.TransactionHelper
import com.splitify.mvc.webhook.WebhookEvent
import groovyx.net.http.RESTClient
import org.apache.log4j.LogManager
import org.apache.log4j.Logger
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

import static groovyx.net.http.ContentType.URLENC

@Service
class FeedService {

    private static final Logger logger = LogManager.getLogger(FeedService);

    @Autowired
    FriendsRepository friendsRepository

    void createDummyFeed(String accountId, String accessToken = null) {
        logger.info("Creating feed for account $accountId")
        sendFeed(accountId,"Just a Dummy Feed","http://google.com","http://www.nyan.cat/cats/original.gif",accessToken)
    }

    void sendSplitAsk(WebhookEvent transaction) {

        def accountId = transaction.accountId
        def accessToken = friendsRepository.getFriendByAccountId(accountId).accessToken //TODO should be provided in another way
        def transactionId = transaction.transactionId

        logger.info("Creating split ask feed for account $accountId")

        def amount = TransactionHelper.prettifyAmount(transaction.amount)
        def title = "Do you want to split your ${amount} ${transaction.currency} transaction?"

        def url = "https://mondo-splitify.herokuapp.com/splitAsk?transactionId=${transactionId}&accountId=${accountId}&amount=${amount}" //TODO dynamic base URL
        sendFeed(accountId,title,url,"http://www.nyan.cat/cats/original.gif", accessToken)
    }

    void askMoneyToFriend(Friend friend, def amount, def currency = "£") {
        logger.info("Creating money back to friend")

        def accountId = friend.accountId
        def accessToken = friend.accessToken
        amount = TransactionHelper.prettifyAmount(amount)
        def title = "Where is my money ($amount $currency) ?"

        def url = "http://google.com"
        sendFeed(accountId,title,url,"http://i.imgur.com/gkWkTtI.gif", accessToken)
    }

    private sendFeed(String accountId,String title, String URL, String imageURL, String accessToken = null) {

        def mondoClient = getFeedClient(accessToken)

        def response = mondoClient.post(
                path:"/feed",
                body: [ "account_id": "$accountId",
                        "type": "basic",
                        "url": "$URL",
                        "params[title]":  "$title",
                        "params[image_url]": "$imageURL"

                ],
                requestContentType: URLENC
        )
    }

    RESTClient getFeedClient(String accessToken) {
    //TODO Load access Token
    return  new MondoAPIClient(accessToken)
}

}
