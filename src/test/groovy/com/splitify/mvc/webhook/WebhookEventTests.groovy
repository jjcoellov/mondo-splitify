package com.splitify.mvc.webhook

import org.junit.Test

class WebhookEventTests {

    @Test
    void testParse() {

        def payload = """
{
    "type": "transaction.created",
    "data": {
        "id": "tx_000097FzXh4Q2VkGFdUbS5",
        "created": "2016-04-16T12:47:28.195Z",
        "description": "PRET A MANGER          LONDON        GBR",
        "amount": -599,
        "currency": "GBP",
        "merchant": {
            "id": "merch_00009438teihh17R68Cu6D",
            "group_id": "grp_00009438temFTpwMxw5ldx",
            "created": "2016-01-11T14:24:44.088Z",
            "name": "Pret A Manger",
            "logo": "http://mondo-logo-cache.appspot.com/twitter/pret/?size=large",
            "emoji": "🍻",
            "category": "eating_out",
            "online": false,
            "atm": false,
            "address": {
                "short_formatted": "173 Victoria St., London Sw1e 5na, Andeby ",
                "formatted": "173 Victoria St., London Sw1e 5na, Andeby , United Kingdom",
                "address": "173 Victoria St., London SW1E 5NA",
                "city": "andeby ",
                "region": "",
                "country": "GBR",
                "postcode": "",
                "latitude": 51.4963204740182,
                "longitude": -0.1422858238220215,
                "zoom_level": 17,
                "approximate": false
            },
            "updated": "2016-03-29T11:07:36.273Z",
            "metadata": {
                "created_for_merchant": "merch_00009438teihh17R68Cu6D",
                "created_for_transaction": "tx_00009438teI7HrpzSTzhg1",
                "foursquare_category": "Sandwich Place",
                "foursquare_category_icon": "https://ss3.4sqi.net/img/categories_v2/food/deli_88.png",
                "foursquare_id": "4bfbb15565fbc9b6fba8916c",
                "foursquare_website": "https://plus.google.com/u/0/b/105098300116703889623/105098300116703889623",
                "google_places_icon": "https://maps.gstatic.com/mapfiles/place_api/icons/restaurant-71.png",
                "google_places_id": "ChIJLx1DIiAFdkgRKPts4r5M_4A",
                "google_places_name": "Pret A Manger",
                "suggested_name": "Pret A Manger",
                "suggested_tags": "#food",
                "twitter_id": "pret",
                "website": "http://www.pret.co.uk/en-gb/find-a-pret/EC2M%202PB/"
            }
        },
        "notes": "",
        "metadata": {},
        "account_balance": 4401,
        "attachments": null,
        "category": "eating_out",
        "is_load": false,
        "settled": "2016-04-16T12:47:28.195Z",
        "local_amount": -599,
        "local_currency": "GBP",
        "updated": "2016-04-16T12:47:28.252Z",
        "account_id": "acc_000097FviDVFuV5Sga5k81",
        "counterparty": {},
        "scheme": "gps_mastercard",
        "dedupe_id": "577783250133656",
        "originator": false
    }
}
"""

        def event = WebhookEvent.parse(payload)
        assert event.accountId == "acc_000097FviDVFuV5Sga5k81"
        assert event.description == "PRET A MANGER          LONDON        GBR"
        assert event.amount == -599
        assert event.currency == "GBP"
        assert event.merchantAddress == "173 Victoria St., London Sw1e 5na, Andeby "
        assert event.merchantName == "Pret A Manger"
    }
}
