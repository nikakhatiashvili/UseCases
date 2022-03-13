package com.example.usecases.ui

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.net.wifi.WifiManager
import android.util.Log
import androidx.lifecycle.*
import com.example.usecases.model.CryptoItem
import com.example.usecases.useCase.GetCryptoUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import java.util.*
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(private val getCryptoUseCase: GetCryptoUseCase) :
    ViewModel() {
    suspend fun getMarket() = getCryptoUseCase().stateIn(viewModelScope)

    //    fun triggerFlow():Flow<List<CryptoItem>>{
//        return flow {
//            while (true){
//                val list = getCryptoUseCase.invoke()
//                list.collect{
//                    emit(it)
//                }
//                delay(15000)
//            }
//        }
//    }
    fun tickFlow(millis: Long) = callbackFlow<List<CryptoItem>> {
        val timer = Timer()
        var time = 0
        timer.scheduleAtFixedRate(
            object : TimerTask() {
                override fun run() {
                    try {
                        val list = getCryptoUseCase.invoke()
                        viewModelScope.launch {
                            list.collect {
                                send(it)
                            }
                        }
                    } catch (e: Exception) {
                    }
                    time += 1
                }
            },
            0,
            millis
        )
        awaitClose {
            timer.cancel()
        }
    }

}