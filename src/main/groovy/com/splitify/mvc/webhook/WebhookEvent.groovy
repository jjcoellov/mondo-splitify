package com.splitify.mvc.webhook

import groovy.json.JsonSlurper
import groovy.transform.Canonical

@Canonical
class WebhookEvent {

    String accountId

    String transactionId

    String description

    String merchantName

    String merchantAddress

    BigInteger amount

    String currency

    static WebhookEvent parse(String payload) {
        def JsonSlurper jsonSlurper = new JsonSlurper()
        def parsedNotification = jsonSlurper.parseText(payload)
        return new WebhookEvent(
                accountId: parsedNotification.data.account_id,
                transactionId: parsedNotification.data.id,
                description: parsedNotification.data.description,
                merchantName: parsedNotification.data.merchant?.name,
                merchantAddress: parsedNotification.data.merchant?.address?.short_formatted,
                amount: parsedNotification.data.amount,
                currency: parsedNotification.data.currency
        )
    }

}
