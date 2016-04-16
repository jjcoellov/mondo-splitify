package com.splitify.mvc.client

import groovyx.net.http.RESTClient
import org.junit.Test


class MondoAPIClientTests {


    @Test
    void basicClientUsage() {
        def mondoClient = new MondoAPIClient("eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJjaSI6Im9hdXRoY2xpZW50XzAwMDA5NFB2SU5ER3pUM2s2dHo4anAiLCJleHAiOjE0NjA5Nzg2ODksImlhdCI6MTQ2MDgwNTg4OSwianRpIjoidG9rXzAwMDA5N0ZzQVFyUFJTR1RkYk11Q3YiLCJ1aSI6InVzZXJfMDAwMDk3RnJHS1ZWc01ad0xVOXg0YiIsInYiOiI0In0.2-x9BJLQ-nqEX_I3K4rptVCimYbbKGCTpXxxMblpk4Y")

        def response = mondoClient.get(path:"ping/whoami")
        assert response.status == 200

        println response.data

    }







}
