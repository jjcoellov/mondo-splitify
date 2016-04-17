package com.splitify.mvc.transaction

import com.splitify.mvc.client.MondoAPIClient
import org.springframework.stereotype.Service

@Service
class TransactionService {

    def retrieveTransaction(String accessToken, String transactionId) {

        def response = new MondoAPIClient(accessToken).get(
                path: "/transactions/$transactionId",
        )

        return response.responseData.transaction
    }

}
