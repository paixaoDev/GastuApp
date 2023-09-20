package com.paixao.dev.gastu.domain.usecases

import com.paixao.dev.gastu.domain.entities.HomeEntity
import com.paixao.dev.gastu.domain.repository.UserRepository
import com.paixao.dev.gastu.domain.util.Result
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import java.math.BigDecimal
import javax.inject.Inject

class PopulateHeaderUseCase @Inject constructor(
    private val repository: UserRepository
) {
    operator fun invoke(): Flow<Result<HomeEntity, String>> = flow {
        try {
            val user = repository.loadUser(0)

            val earningCurrency = user.earnings?.sumOf {
                it.info.value.toBigDecimal()
            } ?: BigDecimal.ZERO

            val spendCurrency = user.spends?.sumOf {
                it.info.value.toBigDecimal()
            } ?: BigDecimal.ZERO

            val currency = earningCurrency - spendCurrency

            val spends = user.earnings?.let { earnings -> user.spends?.let { earnings + it } }

            spends?.sortedByDescending { it.info.date }

            emit(
                Result.Success(
                    HomeEntity(
                        currency = currency.toString(),
                        spendCurrency = spendCurrency.toString(),
                        earningCurrency = earningCurrency.toString(),
                        deals = spends
                    )
                )
            )
        } catch (t: Throwable) {
            emit(Result.Fail(t))
        }
    }
}
