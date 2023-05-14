package com.ecommerce.api.ecommerce.repository.r2dbc

import com.ecommerce.api.ecommerce.entity.EmailAuth
import org.springframework.data.r2dbc.repository.R2dbcRepository

interface EmailAuthRepository: R2dbcRepository<EmailAuth, Int>{
}