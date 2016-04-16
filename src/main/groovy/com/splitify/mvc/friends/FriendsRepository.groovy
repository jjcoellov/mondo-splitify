package com.splitify.mvc.friends

import org.springframework.stereotype.Repository

@Repository
class FriendsRepository {

    List<Friend> retrieveFriends(List<String> friendIds) {

        List<Friend> friends = [
                new Friend(id: "user_000097FvbuwM0vDPEpGOOX", name: "Juanjo", accountId: "acc_000097FviDVFuV5Sga5k81"),
                new Friend(id: "user_000097Fr4zvQ3koVSpNBXV", name: "Alejandro", accountId: "acc_000097FrDMDP4MMq94UFRB"),
                new Friend(id: "user_000097FrGKVVsMZwLU9x4b", name: "Juanma", accountId: "acc_000097FrOUX6U6VVkwnMnp"),
                new Friend(id: "user_000097Fz6bciBPd8EyptDN", name: "Esau", accountId: "acc_000097GCbnqw3xhSmzmntR")
        ]

        return friends.findAll { friendIds.contains(it.id) }
    }
}
