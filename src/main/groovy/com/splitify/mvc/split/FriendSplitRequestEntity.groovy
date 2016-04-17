package com.splitify.mvc.split

import com.splitify.mvc.friends.FriendEntity

//@Entity
class FriendSplitRequestEntity {

    // TODO belongsTo?
    SplitRequestEntity source

    Long id

    FriendEntity debtor

    Long amount

    String currency

    Integer status
}
