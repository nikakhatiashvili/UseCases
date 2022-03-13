package com.example.usecases.di

import com.example.usecases.network.CryptoNetwork
import com.example.usecases.repo.CryptoRepository
import com.example.usecases.repo.CryptoRepositoryImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepoModel {

    @Provides
    @Singleton
    fun provideCryptoRepository(network: CryptoNetwork): CryptoRepository {
        return CryptoRepositoryImpl(network)
    }

}