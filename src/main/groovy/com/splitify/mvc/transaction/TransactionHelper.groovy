package com.splitify.mvc.transaction

class TransactionHelper {

    static String prettifyAmount(BigInteger amount) {

        return new BigDecimal(amount,2).abs()

    }
}
