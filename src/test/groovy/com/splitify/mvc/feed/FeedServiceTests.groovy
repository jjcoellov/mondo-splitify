package com.splitify.mvc.feed

import groovyx.net.http.RESTClient
import org.junit.Test


class FeedServiceTests {



    @Test
    void dummyTest() {

        def mondoClient = new RESTClient("https://staging-api.gmon.io/")
        mondoClient.defaultRequestHeaders['Authorization'] = "Bearer eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJjaSI6Im9hdXRoY2xpZW50XzAwMDA5NFB2SU5ER3pUM2s2dHo4anAiLCJleHAiOjE0NjA5Nzg2ODksImlhdCI6MTQ2MDgwNTg4OSwianRpIjoidG9rXzAwMDA5N0ZzQVFyUFJTR1RkYk11Q3YiLCJ1aSI6InVzZXJfMDAwMDk3RnJHS1ZWc01ad0xVOXg0YiIsInYiOiI0In0.2-x9BJLQ-nqEX_I3K4rptVCimYbbKGCTpXxxMblpk4Y"

        def response = mondoClient.get(path:"ping/whoami")
        assert response.status == 200

        println response.data

    }

    @Test
    void sendDummyFeed() {

        def accountId = "acc_000097FrOUX6U6VVkwnMnp"
        new FeedService().createDummyFeed(accountId)
        assert true
    }

}
