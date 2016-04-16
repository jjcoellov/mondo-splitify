package com.splitify.mvc.feed

import com.splitify.mvc.friends.FriendsRepository
import com.splitify.mvc.webhook.WebhookEvent
import org.junit.Test

class FeedServiceTests {


    @Test
    void sendDummyFeed() {

        def accountId = "acc_000097FrOUX6U6VVkwnMnp"
        new FeedService(friendsRepository: new FriendsRepository()).createDummyFeed(accountId)
        assert true
    }


    @Test
    void sendSplitAskFeed() {

        WebhookEvent transaction = new WebhookEvent(accountId: "acc_000097FrOUX6U6VVkwnMnp", amount: 100, currency: "£" )

        new FeedService(friendsRepository: new FriendsRepository()).sendSplitAsk(transaction)
        assert true
    }
}
