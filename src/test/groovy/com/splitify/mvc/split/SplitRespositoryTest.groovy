package com.splitify.mvc.split

import spock.lang.Ignore
import spock.lang.Specification

@Ignore
class SplitRespositoryTest extends Specification {

    private SplitRepository splitRepository = new SplitRepository()

    def "Test repository works"() {
        given:
            SplitRequest splitRequest = new SplitRequest(
                    transactionId: "tx_123", accountId: "acc_123", friends: ["f_1", "f_2", "f_3"])
        when:
            splitRepository.storeSplitRequestSent(splitRequest)

//        and:
//            SplitRequest stored = splitRepository.retrieveSplitRequestByTransaction("tx_123")

        then:
//            stored.accountId == splitRequest.accountId
//            stored.transactionId == splitRequest.transactionId
//            // TODO: Check friends
            true
    }
}
