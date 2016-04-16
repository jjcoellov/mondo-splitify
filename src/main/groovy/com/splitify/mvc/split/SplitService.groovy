package com.splitify.mvc.split

import com.splitify.mvc.client.MondoAPIClient
import com.splitify.mvc.friends.Friend
import com.splitify.mvc.friends.FriendsRepository
import org.apache.log4j.LogManager
import org.apache.log4j.Logger
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class SplitService {

    private static final Logger logger = LogManager.getLogger(SplitService);

    static final String ACC_TOKEN = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJjaSI6Im9hdXRoY2xpZW50XzAwMDA5NFB2SU5ER3pUM2s2dHo4anAiLCJleHAiOjE0NjA5ODEyMTcsImlhdCI6MTQ2MDgwODQxNywianRpIjoidG9rXzAwMDA5N0Z2dlhXdWxMR3NVczlqdFoiLCJ1aSI6InVzZXJfMDAwMDk3RnZidXdNMHZEUEVwR09PWCIsInYiOiI0In0.DlWlJ3TsRNz8TVFjpn2H653d9N4ZXgthd86GfcyckUI"

    @Autowired
    FriendsRepository friendRepository

    def split(SplitRequest splitRequest) {

        Integer amount = retrieveTransactionAmount(ACC_TOKEN, splitRequest.transactionId)

        List<Friend> friendsToSplit = friendRepository.retrieveFriends(splitRequest.friends)

        Integer amountPerFriend = amount / (friendsToSplit.size() + 1)

        friendsToSplit.each { friend ->
            logger.info(friend.name + " will be notified to pay " + amountPerFriend)
            // send to create friends feeds
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
