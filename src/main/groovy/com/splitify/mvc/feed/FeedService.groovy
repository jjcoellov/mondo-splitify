package com.splitify.mvc.feed

import com.splitify.mvc.client.MondoAPIClient
import groovyx.net.http.RESTClient
import org.apache.log4j.LogManager
import org.apache.log4j.Logger
import org.springframework.stereotype.Service

import static groovyx.net.http.ContentType.URLENC

@Service
class FeedService {


    private static final Logger logger = LogManager.getLogger(FeedService);

    void createDummyFeed(String accountId, String accessToken = null) {
        logger.info("Creating feed for account $accountId")
        sendFeed(accountId,"test","http://google.com","http://www.nyan.cat/cats/original.gif",accessToken)
    }

    private sendFeed(String accountId,String title, String URL, String imageURL, String accessToken = null) {

        def mondoClient = getFeedClient(accessToken)

        def response = mondoClient.post(
                path:"/feed",
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
        return  new MondoAPIClient(accessToken)
    }

}
