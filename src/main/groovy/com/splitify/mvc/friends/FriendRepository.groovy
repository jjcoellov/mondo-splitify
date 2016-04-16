package com.splitify.mvc.friends

import org.springframework.stereotype.Repository

@Repository
class FriendRepository {

    List<Friend> retrieveFriends(List<String> friendIds) {

        List<Friend> friends = [
                new Friend(id: "1", name: "Alejandro", accountId: "1212121211"),
                new Friend(id: "2", name: "Juanma", accountId: "34343434343"),
                new Friend(id: "user_000097Fz6bciBPd8EyptDN", name: "Esau", accountId: "acc_000097GCbnqw3xhSmzmntR")
        ]

        return friends.findAll { friendIds.contains(it.id) }
    }
}
