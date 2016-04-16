package com.splitify.mvc.feed

import groovyx.net.http.RESTClient
import org.junit.Test


class FeedServiceTests {



    @Test
    void sendDummyFeed() {

        def accountId = "acc_000097FrOUX6U6VVkwnMnp"
        new FeedService().createDummyFeed(accountId)
        assert true
    }

}
