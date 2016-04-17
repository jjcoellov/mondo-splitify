package com.splitify.mvc.split

import com.splitify.mvc.friends.FriendEntity

import javax.persistence.*

//@Entity
class SplitRequestEntity implements Serializable {

//    @Id
//    @GeneratedValue
    Long id

    String transactionId

    String accountId

    FriendEntity owner

    Integer amount

    String currency

    // TODO hasMany?
    List<FriendSplitRequestEntity> splits

}
