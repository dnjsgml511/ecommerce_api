package com.ecommerce.api.ecommerce.entity

import org.springframework.data.annotation.Id
import org.springframework.data.relational.core.mapping.Table
import java.time.LocalDateTime

@Table("member")
data class Member(
    @Id
    val memberNo: Int ?= null,
    val memberId: String,
    val memberPw: String,
    val memberName: String,
    val memberState: String,
    val createDate: LocalDateTime,
    val recentLogin: String?,
    val email: String,
)
