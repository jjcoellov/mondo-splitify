package com.splitify.mvc.feed

import com.splitify.mvc.friends.Friend
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

    @Test
    void sendAskMoneyFeed() {

        WebhookEvent transaction = new WebhookEvent(accountId: "acc_000097FrOUX6U6VVkwnMnp", amount: 100, currency: "£" )

        Friend friend = new Friend(
                id: "user_000097FrGKVVsMZwLU9x4b",
                name: "Juanma",
                accountId: "acc_000097FrOUX6U6VVkwnMnp",
                accessToken: "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJjaSI6Im9hdXRoY2xpZW50XzAwMDA5NFB2SU5ER3pUM2s2dHo4anAiLCJleHAiOjE0NjA5Nzg2ODksImlhdCI6MTQ2MDgwNTg4OSwianRpIjoidG9rXzAwMDA5N0ZzQVFyUFJTR1RkYk11Q3YiLCJ1aSI6InVzZXJfMDAwMDk3RnJHS1ZWc01ad0xVOXg0YiIsInYiOiI0In0.2-x9BJLQ-nqEX_I3K4rptVCimYbbKGCTpXxxMblpk4Y"
        )

        new FeedService(friendsRepository: new FriendsRepository()).askMoneyToFriend(friend,6969)
        assert true
    }
}
