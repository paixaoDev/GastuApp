package com.paixao.dev.gastu.domain.usecases

import com.paixao.dev.gastu.domain.repository.DealRepository
import com.paixao.dev.gastu.domain.util.Result
import com.paixao.dev.gastu.presentation.model.DealModel
import com.paixao.dev.gastu.presentation.model.toEntity
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class CreateDealUseCase @Inject constructor(
    private val repository: DealRepository
) {
    operator fun invoke(deal: DealModel): Flow<Result<String>> = flow {
        try {
            repository.saveUserDeal(deal.toEntity())
            emit(Result.Success(""))
        } catch (t: Throwable) {

        }
    }
}