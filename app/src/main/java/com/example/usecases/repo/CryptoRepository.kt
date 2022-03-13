package com.example.usecases.repo

import com.example.usecases.model.CryptoItem
import kotlinx.coroutines.flow.Flow

interface CryptoRepository {
    fun getMarket(): Flow<List<CryptoItem>>
}