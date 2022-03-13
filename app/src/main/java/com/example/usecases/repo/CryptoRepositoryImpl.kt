package com.example.usecases.repo

import com.example.usecases.model.CryptoItem
import com.example.usecases.network.CryptoNetwork
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class CryptoRepositoryImpl@Inject constructor(private val homeNetwork: CryptoNetwork):CryptoRepository {

    override fun getMarket(): Flow<List<CryptoItem>> = flow {
        emit(homeNetwork.getCoinList().body()!!)
    }.flowOn(Dispatchers.IO)
}