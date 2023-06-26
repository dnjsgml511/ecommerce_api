package com.ecommerce.api.ecommerce.service.seller

import com.ecommerce.api.ecommerce.entity.Seller
import com.ecommerce.api.ecommerce.repository.r2dbc.SellerRepository
import kotlinx.coroutines.reactor.awaitSingleOrNull
import org.springframework.stereotype.Component

@Component
class getSeller(
    private val sellerRepository: SellerRepository,
) {

    suspend fun getSeller(sellerNo: Int): Seller? {
        return sellerRepository.findBySellerNo(sellerNo).awaitSingleOrNull()
    }

}