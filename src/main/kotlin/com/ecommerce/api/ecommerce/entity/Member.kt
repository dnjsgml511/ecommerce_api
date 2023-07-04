package com.ecommerce.api.ecommerce.entity

import com.ecommerce.api.ecommerce.dto.req.SignupReqDto
import org.springframework.data.annotation.Id
import org.springframework.data.relational.core.mapping.Table
import java.time.LocalDate
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
    val birthDate: LocalDate,
    val recentLogin: String?,
    val email: String,
){
    constructor(reqDto: SignupReqDto) : this(
        memberId = reqDto.memberId,
        memberPw = reqDto.memberPw,
        memberName = reqDto.memberName,
        memberState = "200",
        createDate = LocalDateTime.now(),
        birthDate = reqDto.birthDate,
        recentLogin = null,
        email = reqDto.email
    )

}
