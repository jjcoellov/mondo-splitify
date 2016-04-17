package com.splitify.mvc.split

import com.splitify.mvc.friends.FriendEntity
import com.sun.xml.internal.bind.v2.model.core.ID

import javax.persistence.*

//@Entity(name="split_request_friend")
class FriendSplitRequestEntity {

//    @Id
//    @Column(name="split_request_friend_id", nullable = false)
    Long id

//    @ManyToOne
//    @JoinColumn(name="split_request_id", nullable=false)
    SplitRequestEntity source

//    @JoinColumn(name="friend_id", nullable = false)
    FriendEntity debtor

//    @Column(name="amount", nullable = false)
    Long amount

//    @Column(name="currency", nullable = false)
    String currency

//    @Column(name="status", nullable = false)
    Integer status
}
