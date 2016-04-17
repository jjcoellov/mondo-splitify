package com.splitify.mvc.webhook

import com.splitify.mvc.client.MondoAPIClient
import com.splitify.mvc.friends.Friend
import groovyx.net.http.ContentType
import org.apache.log4j.LogManager
import org.apache.log4j.Logger
import org.springframework.stereotype.Service

@Service
class WebhookService {

    private final static String WEBHOOK_URL = "https://mondo-splitify.herokuapp.com/notify"

    private static final Logger logger = LogManager.getLogger(WebhookService);

    void registerWebhook(Friend friend) {
        logger.info("Registering webhook for user ${friend.name} (account: ${friend.accountId})")

        try {
            def webhook = findWebhook(friend)
            if (webhook) {
                logger.info("Webhook found for the application. Skipping")
            } else {
                doRegister(friend)
                logger.info("Webhook registered")
            }
        } catch (Exception exception) {
            logger.error("Error attempting to unregister webhook for user ${friend.name} (${friend.id})", exception)
        }
    }

    void unregisterWebhook(Friend friend) {
        logger.info("Unegistering webhook for user ${friend.name} (account: ${friend.accountId})")

        try {
            def webhook = findWebhook(friend)
            if (webhook) {
                doUnregister(friend, webhook.id)
                logger.info("Webhook deleted")
            } else {
                logger.info("Webhook not found for the application. Skipping")
            }
        } catch (Exception exception) {
            logger.error("Error attempting to unregister webhook for user ${friend.name} (${friend.id})", exception)
        }
    }

    private def findWebhook(Friend friend) {

        MondoAPIClient mondoAPIClient = new MondoAPIClient(friend.accessToken)
        def response = mondoAPIClient.get(
                path: "/webhooks",
                query: [
                        account_id: friend.accountId
                ]
        )

        return response.responseData.webhooks.find { it.url == WEBHOOK_URL }
    }

    private void doRegister(Friend friend) {
        MondoAPIClient mondoAPIClient = new MondoAPIClient(friend.accessToken)

        mondoAPIClient.post(
                path:"/webhooks",
                body: [ "account_id": "${friend.accountId}",
                        "url": "${WEBHOOK_URL}",
                ],
                requestContentType: ContentType.URLENC

        )
    }

    private void doUnregister(Friend friend, String webhookId) {
        MondoAPIClient mondoAPIClient = new MondoAPIClient(friend.accessToken)

        mondoAPIClient.delete(
                path:"/webhooks/${webhookId}",
        )
    }
}
