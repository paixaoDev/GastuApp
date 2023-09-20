package com.paixao.dev.gastu.domain.usecases

import com.paixao.dev.gastu.domain.repository.DealRepository
import com.paixao.dev.gastu.domain.util.Result
import com.paixao.dev.gastu.presentation.model.DealModel
import com.paixao.dev.gastu.presentation.model.toEntity
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class UpdateDealUseCase  @Inject constructor(
    private val repository: DealRepository
) {
    operator fun invoke(deal: DealModel): Flow<Result<String, String>> = flow {
        try {
            repository.updateUserDeal(deal.toEntity())
            emit(Result.Success(""))
        } catch (t: Throwable) {

        }
    }
}