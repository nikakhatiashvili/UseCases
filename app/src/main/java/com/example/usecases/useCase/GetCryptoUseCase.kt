package com.example.usecases.useCase

import com.example.usecases.model.CryptoItem
import com.example.usecases.repo.CryptoRepository
import com.example.usecases.repo.CryptoRepositoryImpl
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetCryptoUseCase @Inject constructor(
    private val repository: CryptoRepository
) {
    operator fun invoke(): Flow<List<CryptoItem>> {
        return repository.getMarket()
    }
}