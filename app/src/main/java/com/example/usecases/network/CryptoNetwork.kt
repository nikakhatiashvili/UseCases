package com.example.usecases.network

import com.example.usecases.model.CryptoItem
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface CryptoNetwork {


    @GET("v3/coins/markets")
    suspend fun getCoinList(@Query("vs_currency") currency: String = CURRENCY,
                            @Query("order") order:String = MARKET_CAP,
                            @Query("per_page") page:String = NUMBER_OF_CRYPTO,
                            @Query("page")pages:String = "1",
                            @Query("sparkline")spark:String = "false"): Response<List<CryptoItem>>
}