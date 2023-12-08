package com.paixao.dev.gastu.domain.usecases

import com.paixao.dev.gastu.domain.entities.DealEntity
import com.paixao.dev.gastu.domain.entities.UserEntity
import com.paixao.dev.gastu.domain.repository.DealRepository
import com.paixao.dev.gastu.domain.repository.UserRepository
import com.paixao.dev.gastu.domain.util.DealTypeEnum
import com.paixao.dev.gastu.domain.util.Result
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class CreateDealUseCase @Inject constructor(
    private val userRepository: UserRepository,
    private val repository: DealRepository
) {
    operator fun invoke(deal: DealEntity): Flow<Result<String, String>> = flow {
        try {
            val user = userRepository.loadUser("0")
            user?.let { userSafe ->
                var spend = userSafe.spendCurrency
                var earning = userSafe.earningCurrency

                when (deal.dealType) {
                    DealTypeEnum.Spend.name -> {
                        spend += deal.value
                    }

                    DealTypeEnum.Earning.name -> {
                        earning += deal.value
                    }
                }

                val currency = earning - spend

                userRepository.updateUser(
                    UserEntity(
                        id = userSafe.id,
                        currency = currency,
                        spendCurrency = spend,
                        earningCurrency = earning
                    )
                )
            }
            repository.saveUserDeal(deal)
            emit(Result.Success(""))
        } catch (t: Throwable) {
            emit(Result.Fail(t))
        }
    }
}