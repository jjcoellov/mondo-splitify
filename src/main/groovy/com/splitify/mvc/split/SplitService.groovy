package com.splitify.mvc.split

import com.splitify.mvc.feed.FeedService
import com.splitify.mvc.friends.Friend
import com.splitify.mvc.friends.FriendsRepository
import com.splitify.mvc.transaction.TransactionService
import org.apache.log4j.LogManager
import org.apache.log4j.Logger
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class SplitService {

    private static final Logger logger = LogManager.getLogger(SplitService);

    @Autowired
    FriendsRepository friendRepository

    @Autowired
    TransactionService transactionService

    @Autowired
    FeedService feedService

    def split(SplitRequest splitRequest) {

        String accessToken = friendRepository.getFriendByAccountId(splitRequest.accountId).accessToken
        String transactionId = splitRequest.transactionId
        Integer amount = transactionService.retrieveTransaction(accessToken, splitRequest.transactionId).amount

        List<Friend> friendsToSplit = friendRepository.retrieveFriends(splitRequest.friends)

        Integer amountPerFriend = amount / (friendsToSplit.size() + 1)

        friendsToSplit.each { friend ->
            logger.info(friend.name + " will be notified to pay " + amountPerFriend)
            feedService.askMoneyToFriend(friend, transactionId, amountPerFriend)
        }
    }

}
