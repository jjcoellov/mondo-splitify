package com.splitify.mvc.split

import com.splitify.mvc.client.MondoAPIClient
import com.splitify.mvc.feed.FeedService
import com.splitify.mvc.friends.Friend
import com.splitify.mvc.friends.FriendsRepository
import org.apache.log4j.LogManager
import org.apache.log4j.Logger
import org.springframework.beans.factory.annotation.Autowire
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class SplitService {

    private static final Logger logger = LogManager.getLogger(SplitService);

    @Autowired
    FriendsRepository friendRepository

    @Autowired
    FeedService feedService

    def split(SplitRequest splitRequest) {

        String accessToken = friendRepository.getFriendByAccountId(splitRequest.accountId).accessToken
        Integer amount = retrieveTransactionAmount(accessToken, splitRequest.transactionId)

        List<Friend> friendsToSplit = friendRepository.retrieveFriends(splitRequest.friends)

        Integer amountPerFriend = amount / (friendsToSplit.size() + 1)

        friendsToSplit.each { friend ->
            logger.info(friend.name + " will be notified to pay " + amountPerFriend)
            feedService.askMoneyToFriend(friend,amount)
        }

        // persist split operation
    }

    private static Integer retrieveTransactionAmount(String accessToken, String transactionId) {

        def response = new MondoAPIClient(accessToken).get(
                path: "/transactions/$transactionId",
        )

        return response.responseData.transaction.amount ?: 0
    }

}
