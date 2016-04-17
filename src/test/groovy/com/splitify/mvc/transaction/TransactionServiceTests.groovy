package com.splitify.mvc.transaction

import com.splitify.mvc.friends.Friend
import com.splitify.mvc.friends.FriendsRepository
import org.junit.Test

/**
 * Created by jcoello on 17/04/2016.
 */
class TransactionServiceTests {

    @Test
    void testRetrieve() {

        Friend friend = new FriendsRepository().friends.first()
        def a = new TransactionService().retrieveTransaction(friend.accessToken, "tx_000097IDovyheZ9Tqp8cTp")

        assert a.merchant.name
    }

    @Test
    void testSettleDate() {
        String settleDate = "2016-04-17T14:36:52.698Z"

        String date = settleDate.substring(0, settleDate.indexOf("T"))
        String time = settleDate.substring(settleDate.indexOf("T") + 1, settleDate.indexOf("."))
        assert date == "2016-04-17"
        assert time == "14:36:52"
    }
}
