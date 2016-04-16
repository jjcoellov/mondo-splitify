package com.splitify.mvc.client

import groovyx.net.http.RESTClient


class MondoAPIClient extends RESTClient {

 private static final String baseURL = "https://staging-api.gmon.io/"

    MondoAPIClient(String accessToken) {
        super(baseURL)
        if (!accessToken) {
            accessToken = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJjaSI6Im9hdXRoY2xpZW50XzAwMDA5NFB2SU5ER3pUM2s2dHo4anAiLCJleHAiOjE0NjA5Nzg2ODksImlhdCI6MTQ2MDgwNTg4OSwianRpIjoidG9rXzAwMDA5N0ZzQVFyUFJTR1RkYk11Q3YiLCJ1aSI6InVzZXJfMDAwMDk3RnJHS1ZWc01ad0xVOXg0YiIsInYiOiI0In0.2-x9BJLQ-nqEX_I3K4rptVCimYbbKGCTpXxxMblpk4Y"
        }
        this.defaultRequestHeaders['Authorization'] = "Bearer $accessToken"
    }

}
