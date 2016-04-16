package com.splitify.mvc.feed

import groovyx.net.http.RESTClient

import static groovyx.net.http.ContentType.URLENC


class FeedService {


    void createDummyFeed(String accountId, String accessToken = null) {
        sendFeed(accountId,"test","http://google.com","http://www.nyan.cat/cats/original.gif")
    }

    private sendFeed(String accountId,String title, String URL, String imageURL, String accessToken = null) {

        def mondoClient = getFeedClient(accessToken)

        def response = mondoClient.post(
                //path:"/feed",
                body: [ "account_id": "$accountId",
                        "type": "basic",
                        "url": "$URL",
                        "params[title]":  "$title",
                        "params[image_url]": "$imageURL"

                ],
                requestContentType: URLENC
        )
    }

    RESTClient getFeedClient(String accessToken) {
        def mondoClient = new RESTClient("https://staging-api.gmon.io/feed")
        if (!accessToken) {
            accessToken = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJjaSI6Im9hdXRoY2xpZW50XzAwMDA5NFB2SU5ER3pUM2s2dHo4anAiLCJleHAiOjE0NjA5Nzg2ODksImlhdCI6MTQ2MDgwNTg4OSwianRpIjoidG9rXzAwMDA5N0ZzQVFyUFJTR1RkYk11Q3YiLCJ1aSI6InVzZXJfMDAwMDk3RnJHS1ZWc01ad0xVOXg0YiIsInYiOiI0In0.2-x9BJLQ-nqEX_I3K4rptVCimYbbKGCTpXxxMblpk4Y"
        }
        mondoClient.defaultRequestHeaders['Authorization'] = "Bearer $accessToken"
        return mondoClient

    }

}
