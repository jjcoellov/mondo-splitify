package com.splitify.mvc.split

import groovy.transform.Canonical

@Canonical
class SplitRequest {

    List<String> friends = []

    String accountId

    String transactionId

}
