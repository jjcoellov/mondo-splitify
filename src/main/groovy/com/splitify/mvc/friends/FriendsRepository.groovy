package com.splitify.mvc.friends

import org.springframework.stereotype.Repository

@Repository
class FriendsRepository {

    List<Friend> friends = [
            new Friend(
                    id: "user_000097FvbuwM0vDPEpGOOX",
                    name: "Juanjo",
                    accountId: "acc_000097FviDVFuV5Sga5k81",
                    accessToken: "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJjaSI6Im9hdXRoY2xpZW50XzAwMDA5NFB2SU5ER3pUM2s2dHo4anAiLCJleHAiOjE0NjA5ODEyMTcsImlhdCI6MTQ2MDgwODQxNywianRpIjoidG9rXzAwMDA5N0Z2dlhXdWxMR3NVczlqdFoiLCJ1aSI6InVzZXJfMDAwMDk3RnZidXdNMHZEUEVwR09PWCIsInYiOiI0In0.DlWlJ3TsRNz8TVFjpn2H653d9N4ZXgthd86GfcyckUI",
                    phoneNumber: "+447820707070"
            ),
            new Friend(
                    id: "user_000097Fr4zvQ3koVSpNBXV",
                    name: "Alejandro",
                    accountId: "acc_000097FrDMDP4MMq94UFRB",
                    accessToken: "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJjaSI6Im9hdXRoY2xpZW50XzAwMDA5NFB2SU5ER3pUM2s2dHo4anAiLCJleHAiOjE0NjEwNzY0ODMsImlhdCI6MTQ2MDkwMzY4MywianRpIjoidG9rXzAwMDA5N0lEZDA5NEtsb0xFZEVTUDMiLCJ1aSI6InVzZXJfMDAwMDk3RnI0enZRM2tvVlNwTkJYViIsInYiOiI0In0.pG2-HAz9kiJwROD-9lpK4z1wE4ACr6YdxDgQqufq980",
                    phoneNumber: "+447820707071"
            ),
            new Friend(
                    id: "user_000097FrGKVVsMZwLU9x4b",
                    name: "Juanma",
                    accountId: "acc_000097FrOUX6U6VVkwnMnp",
                    accessToken: "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJjaSI6Im9hdXRoY2xpZW50XzAwMDA5NFB2SU5ER3pUM2s2dHo4anAiLCJleHAiOjE0NjA5Nzg2ODksImlhdCI6MTQ2MDgwNTg4OSwianRpIjoidG9rXzAwMDA5N0ZzQVFyUFJTR1RkYk11Q3YiLCJ1aSI6InVzZXJfMDAwMDk3RnJHS1ZWc01ad0xVOXg0YiIsInYiOiI0In0.2-x9BJLQ-nqEX_I3K4rptVCimYbbKGCTpXxxMblpk4Y",
                    phoneNumber: "+447820707072"

            ),
            new Friend(
                    id: "user_000097Fz6bciBPd8EyptDN",
                    name: "Esau",
                    accountId: "acc_000097GCbnqw3xhSmzmntR",
                    accessToken: "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJjaSI6Im9hdXRoY2xpZW50XzAwMDA5NFB2SU5ER3pUM2s2dHo4anAiLCJleHAiOjE0NjEwNjQ0MjAsImlhdCI6MTQ2MDg5MTYyMCwianRpIjoidG9rXzAwMDA5N0h2Z1dFdGZSN2xUcWNGczEiLCJ1aSI6InVzZXJfMDAwMDk3Rno2YmNpQlBkOEV5cHRETiIsInYiOiI0In0.VU_wnBmxaWENy2GsYx7uLUFd23xSRJE7GtsWAv9Z9wA",
                    phoneNumber: "+447820707073"
            )
    ]

    List<Friend> retrieveFriends(List<String> friendIds) {
        return friends.findAll { friendIds.contains(it.id) }
    }

    List<Friend> retrieveFriendsExcludingByAccount(String accountId) {
        return friends.findAll { it.accountId != accountId }
    }

    Friend getFriendByAccountId(String accountId) {
        return friends.find { it.accountId == accountId }
    }
}
