package com.splitify.service

import org.junit.Test
import org.springframework.web.client.RestTemplate


import groovyx.net.http.RESTClient
import static groovyx.net.http.ContentType.*

class FeedServiceTests {


    static final String accessToken = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJjaSI6Im9hdXRoY2xpZW50XzAwMDA5NFB2SU5ER3pUM2s2dHo4anAiLCJleHAiOjE0NjA5Nzg2ODksImlhdCI6MTQ2MDgwNTg4OSwianRpIjoidG9rXzAwMDA5N0ZzQVFyUFJTR1RkYk11Q3YiLCJ1aSI6InVzZXJfMDAwMDk3RnJHS1ZWc01ad0xVOXg0YiIsInYiOiI0In0.2-x9BJLQ-nqEX_I3K4rptVCimYbbKGCTpXxxMblpk4Y"

    @Test
    void dummyTest() {

        def mondoClient = new RESTClient("https://staging-api.gmon.io/")
        mondoClient.defaultRequestHeaders['Authorization'] = "Bearer eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJjaSI6Im9hdXRoY2xpZW50XzAwMDA5NFB2SU5ER3pUM2s2dHo4anAiLCJleHAiOjE0NjA5Nzg2ODksImlhdCI6MTQ2MDgwNTg4OSwianRpIjoidG9rXzAwMDA5N0ZzQVFyUFJTR1RkYk11Q3YiLCJ1aSI6InVzZXJfMDAwMDk3RnJHS1ZWc01ad0xVOXg0YiIsInYiOiI0In0.2-x9BJLQ-nqEX_I3K4rptVCimYbbKGCTpXxxMblpk4Y"

        def response = mondoClient.get(path:"ping/whoami")
        assert response.status == 200

        println response.data

    }


    @Test
    void dummyTest2() {

        def mondoClient = new RESTClient("https://staging-api.gmon.io/")
        mondoClient.defaultRequestHeaders['Authorization'] = "Bearer eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJjaSI6Im9hdXRoY2xpZW50XzAwMDA5NFB2SU5ER3pUM2s2dHo4anAiLCJleHAiOjE0NjA5Nzg2ODksImlhdCI6MTQ2MDgwNTg4OSwianRpIjoidG9rXzAwMDA5N0ZzQVFyUFJTR1RkYk11Q3YiLCJ1aSI6InVzZXJfMDAwMDk3RnJHS1ZWc01ad0xVOXg0YiIsInYiOiI0In0.2-x9BJLQ-nqEX_I3K4rptVCimYbbKGCTpXxxMblpk4Y"

        def account_id = "acc_000097FrOUX6U6VVkwnMnp"
        def response = mondoClient.post(
                path:"/feed",
                body: [ "account_id": "$account_id",
                        "type": "basic",
                        "url": "http://google.com/",
                         "params[title]":  "example",
                        "params[image_url]": "http://www.nyan.cat/cats/original.gif"

                ],
                requestContentType: URLENC
        )

        response.status == 200
        println response.data

    }







}
