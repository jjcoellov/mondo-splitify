package com.splitify.mvc.transaction

class TransactionHelper {

    static String prettifyAmount(BigInteger amount) {

        return new BigDecimal(amount,2).abs()

    }

    static Boolean isDebit(BigInteger amount) {
        return (amount <= 0)
    }
}
