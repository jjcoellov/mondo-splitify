package com.splitify.mvc.transaction

import org.junit.Test


class TransactionHelperTests {



    @Test
    void "amounts should be presented with two decimals places"() {

        BigInteger amountToBePrettify = 1234

        def prettifiedAmount = TransactionHelper.prettifyAmount(amountToBePrettify)

        assert "12.34" == prettifiedAmount

    }

    @Test
    void "negative amounts should be presented  as positive values"() {

        BigInteger amountToBePrettify = -1000

        def prettifiedAmount = TransactionHelper.prettifyAmount(amountToBePrettify)

        assert "10.00" == prettifiedAmount

    }

    @Test
    void "negative amounts should be considered  debit"() {

        BigInteger amount = -1

        def isDebit = TransactionHelper.isDebit(amount)

        assert isDebit

    }

    @Test
    void "positive amounts should NOT be considered  debit"() {

        BigInteger amount = 1

        def isDebit = TransactionHelper.isDebit(amount)

        assert isDebit == false

    }



}
