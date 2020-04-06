package com.merttoptas.bitcointracker.network

import com.merttoptas.bitcointracker.views.model.CoinModel
import kotlinx.coroutines.Deferred
import retrofit2.http.GET
import retrofit2.http.Query

interface CoinApiService {
    @GET("/v1/public/coins")
    fun getPropertiesAsync(@Query("offset") offset: Int, @Query("limit") limit: Int):
            Deferred<CoinModel>
}
