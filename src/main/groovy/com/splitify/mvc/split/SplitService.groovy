package com.splitify.mvc.split

import com.splitify.mvc.friends.Friend
import com.splitify.mvc.friends.FriendsRepository
import org.apache.log4j.LogManager
import org.apache.log4j.Logger
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class SplitService {

    private static final Logger logger = LogManager.getLogger(SplitService);

    @Autowired
    FriendsRepository friendRepository

    def split(SplitRequest splitRequest) {

        // go to mondo to retrieve transaction info
        Integer amount = -1000

        List<Friend> friendsToSplit = friendRepository.retrieveFriends(splitRequest.friends)

        // divide amount by friends to pay
        Integer amountPerFriend = amount / (friendsToSplit.size() + 1)

        friendsToSplit.each { friend ->
            logger.info(friend.name + " will be notified to pay " + amountPerFriend)
            // send to create friends feeds
        }

        // persist split operation
    }


}
