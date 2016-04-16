package com.splitify.mvc.split

import com.splitify.mvc.friends.Friend
import com.splitify.mvc.friends.FriendsRepository
import com.splitify.mvc.mondo.ApiConfig
import groovyx.net.http.RESTClient
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

        Integer amount = retrieveTransactionAmount(ApiConfig.ACCESS_TOKEN, splitRequest.transactionId)

        List<Friend> friendsToSplit = friendRepository.retrieveFriends(splitRequest.friends)

        Integer amountPerFriend = amount / (friendsToSplit.size() + 1)

        friendsToSplit.each { friend ->
            logger.info(friend.name + " will be notified to pay " + amountPerFriend)
            // send to create friends feeds
        }

        // persist split operation
    }

    private Integer retrieveTransactionAmount(String authToken, String transactionId) {

        def mondoClient = new RESTClient(ApiConfig.URL)
        mondoClient.defaultRequestHeaders['Authorization'] = "Bearer $authToken"

        def response = mondoClient.get(
                path: "/transactions/$transactionId",
        )

        return response.responseData.transaction.amount ?: 0
    }

}
