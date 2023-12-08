package com.paixao.dev.gastu.domain.usecases

import com.paixao.dev.gastu.domain.entities.DealEntity
import com.paixao.dev.gastu.domain.entities.UserEntity
import com.paixao.dev.gastu.domain.repository.DealRepository
import com.paixao.dev.gastu.domain.repository.UserRepository
import com.paixao.dev.gastu.domain.util.Result
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import java.math.BigDecimal
import javax.inject.Inject

class PopulateHomeContentUseCase @Inject constructor(
    private val userRepository: UserRepository,
    private val dealRepository: DealRepository
) {
    operator fun invoke(): Flow<Result<Pair<UserEntity, List<DealEntity>?>, String>> = flow {
        try {
            var user = userRepository.loadUser("0")
            if (user == null) {
                user = userRepository.createUser(
                    UserEntity(
                        "0",
                        BigDecimal.ZERO,
                        BigDecimal.ZERO,
                        BigDecimal.ZERO
                    )
                )
            }

            val deals = dealRepository.loadUserDealList(user.id)
            val sortedDeals = deals.sortedByDescending { it.date }

            emit(
                Result.Success(Pair(user, sortedDeals))
            )
        } catch (t: Throwable) {
            emit(Result.Fail(t))
        }
    }
}
