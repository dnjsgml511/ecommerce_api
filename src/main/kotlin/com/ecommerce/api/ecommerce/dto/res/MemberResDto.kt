package com.ecommerce.api.ecommerce.dto.res

import com.ecommerce.api.ecommerce.entity.Member

data class MemberResDto(
    val memberNo: Int,
    val memberId: String,
    val email: String,
    val memberName: String,
    val memberState: String,
    val birthDate: String,
){
    constructor(member: Member): this(
        memberNo = member.memberNo ?: 0,
        memberId = member.memberId,
        email = member.email,
        memberName = member.memberName,
        memberState = member.memberState,
        birthDate = member.birthDate.toString()
    )
}