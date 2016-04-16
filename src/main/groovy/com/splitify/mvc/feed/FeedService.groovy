package com.splitify.mvc.feed

import com.splitify.mvc.client.MondoAPIClient
import com.splitify.mvc.friends.FriendsRepository
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
        sendFeed(accountId,"test","http://google.com","http://www.nyan.cat/cats/original.gif",accessToken)
    }

    void sendSplitAsk(WebhookEvent transaction) {
        logger.info("Creating split ask feed for account")

        def accountId = transaction.accountId
        def accessToken = friendsRepository.getFriendByAccountId(accountId).accessToken
        def title = "Do you want to split your ${transaction.amount} ${transaction.currency} transaction?"
        def transactionId = transaction.transactionId

        def url = "https://mondo-splitify.herokuapp.com/splitAsk?transactionId=${transactionId}&accountId=${accountId}"
        sendFeed(accountId,title,url,"http://www.nyan.cat/cats/original.gif", accessToken)
    }

    private getAccessToken(WebhookEvent transaction) {


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
