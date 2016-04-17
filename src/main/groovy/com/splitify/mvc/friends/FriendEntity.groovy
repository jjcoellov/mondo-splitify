package com.splitify.mvc.friends

import javax.persistence.*;

@Entity(name="friend")
class FriendEntity implements Serializable {

    @Id
    @GeneratedValue
    @Column(name="friend_id", nullable = false)
    Long friendId

    @Column(name="name", nullable = false)
    String name

    @Column(name="account_id", nullable = false)
    String accountId

    @Column(name="access_token", nullable = false)
    String accessToken

    @Column(name="phone_number", nullable = false)
    String phoneNumber

}
