package com.paixao.dev.gastu.domain.usecases

import com.paixao.dev.gastu.domain.entities.HomeEntity
import com.paixao.dev.gastu.domain.repository.UserRepository
import com.paixao.dev.gastu.domain.util.Result
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class PopulateHeaderUseCase @Inject constructor(
    private val repository: UserRepository
) {
    operator fun invoke(): Flow<Result<HomeEntity>> = flow {
        try {
            val user = repository.loadUser(0)
            val earningCurrency = user.earnings?.sumOf { it.info.value.toLong() } ?: 0L
            val spendCurrency = user.spends?.sumOf { it.info.value.toLong()} ?: 0L
            val currency = earningCurrency - spendCurrency
            val spends = user.earnings?.let { earnings -> user.spends?.let { earnings + it } }
            spends?.sortedByDescending { it.info.date }

            emit(Result.Success(HomeEntity(currency.toFloat(), spendCurrency.toFloat(), earningCurrency.toFloat(), spends)))

        } catch (t: Throwable) {

        }
    }
}
