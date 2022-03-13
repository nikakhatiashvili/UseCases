package com.example.usecases.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.usecases.useCase.GetCryptoUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.stateIn
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(private val getCryptoUseCase: GetCryptoUseCase): ViewModel() {
    suspend fun getMarket() = getCryptoUseCase().stateIn(viewModelScope)
}