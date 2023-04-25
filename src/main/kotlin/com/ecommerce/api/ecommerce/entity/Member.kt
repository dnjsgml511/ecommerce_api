package com.ecommerce.api.ecommerce.entity

import org.springframework.data.annotation.Id
import org.springframework.data.relational.core.mapping.Table

@Table("member")
data class Member(
    @Id
    val memberNo: Int ?= null,
    val memberId: String,
    val memberPw: String,
    val memberName: String,
    val memberState: String,
    val createDate: String,
    val recentLogin: String?
)
