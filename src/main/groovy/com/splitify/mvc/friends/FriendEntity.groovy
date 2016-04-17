package com.splitify.mvc.friends

import javax.persistence.*;

@Entity
class FriendEntity implements Serializable {

    @Id
    @GeneratedValue
    Long friendId

    @Column(nullable = false)
    String name

    @Column(nullable = false)
    String accountId

    @Column(nullable = false)
    String accessToken

    @Column(nullable = false)
    String phoneNumber

}
