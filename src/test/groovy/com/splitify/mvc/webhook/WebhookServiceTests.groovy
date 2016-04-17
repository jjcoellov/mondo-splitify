package com.splitify.mvc.webhook

import com.splitify.mvc.client.MondoAPIClient
import com.splitify.mvc.friends.Friend
import com.splitify.mvc.friends.FriendsRepository
import org.junit.Test

class WebhookServiceTests {

    @Test
    void testRegisterWebhookIsIdempotent() {
        Friend friend = new FriendsRepository().friends.last()

        WebhookService webhookService = new WebhookService()
        webhookService.registerWebhook(friend)
        def numberOfWebhooks = listWebhooks(friend).size()

        webhookService.registerWebhook(friend)
        assert numberOfWebhooks == listWebhooks(friend).size(), "There should not be extra webhooks when attempting to register twice"
    }

    @Test
    void testUnregisterWebhookIsIdemportent() {
        Friend friend = new FriendsRepository().friends.last()

        WebhookService webhookService = new WebhookService()
        webhookService.unregisterWebhook(friend)
        def numberOfWebhooks = listWebhooks(friend).size()

        webhookService.unregisterWebhook(friend)
        assert numberOfWebhooks == listWebhooks(friend).size(), "There should not be less webhooks when attempting to unregister twice"
    }

    private def listWebhooks(Friend friend) {
        MondoAPIClient mondoAPIClient = new MondoAPIClient(friend.accessToken)
        def response = mondoAPIClient.get(
                path: "/webhooks",
                query: [
                        account_id: friend.accountId
                ]
        )
        return response.responseData.webhooks
    }

}
