package com.ecommerce.api.ecommerce.repository

import com.ecommerce.api.ecommerce.entity.Member
import org.springframework.data.repository.reactive.ReactiveCrudRepository

interface MemberRepository: ReactiveCrudRepository<Member, Int> {
}