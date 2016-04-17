package com.splitify.mvc.split

import com.splitify.mvc.friends.FriendEntity

import javax.persistence.*

//@Entity(name="split_request")
class SplitRequestEntity implements Serializable {

//    @Id
//    @GeneratedValue
//    @Column(name='split_request_id', nullable=false)
    Long id

//    @Column(name='transaction_id', nullable=false)
    String transactionId

//    @Column(name='account_id', nullable=false)
    String accountId

//    @JoinColumn(name='owner_id', nullable=false)
    FriendEntity owner

//    @Column(name='amount', nullable=false)
    Integer amount

//    @Column(name='currency', nullable=false)
    String currency

//    @OneToMany(mappedBy='source', targetEntity = FriendSplitRequestEntity.class, fetch = FetchType.EAGER)
    List<FriendSplitRequestEntity> splits

}
