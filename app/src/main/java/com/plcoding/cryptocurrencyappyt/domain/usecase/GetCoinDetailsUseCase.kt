package com.plcoding.cryptocurrencyappyt.domain.usecase

import com.plcoding.cryptocurrencyappyt.common.Resource
import com.plcoding.cryptocurrencyappyt.data.remote.dto.toCoinDetail
import com.plcoding.cryptocurrencyappyt.domain.model.CoinDetail
import com.plcoding.cryptocurrencyappyt.domain.repository.CoinRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class GetCoinDetailsUseCase @Inject constructor(private val repository: CoinRepository) {

    operator fun invoke(coinId: String): Flow<Resource<CoinDetail>> = flow {
        try {
            emit(Resource.Loading<CoinDetail>())
            val coinDetail = repository.getCoinById(coinId)
            emit(Resource.Success<CoinDetail>(data = coinDetail.toCoinDetail()))
        } catch (e: HttpException) {
            emit(Resource.Error<CoinDetail>(message = e.localizedMessage ?: "Unexpected error occurred"))
        } catch (e: IOException) {
            emit(Resource.Error<CoinDetail>(message = "Couldn't reach network. Check internet connection"))
        }
    }
}