package com.merttoptas.bitcointracker.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.merttoptas.bitcointracker.network.CoinApiStatus
import com.merttoptas.bitcointracker.network.api.CoinApi
import com.merttoptas.bitcointracker.views.model.Coin
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch



class CoinViewModel :ViewModel() {
    private var viewModelJob = Job()
    private val coroutineScope = CoroutineScope(
        viewModelJob + Dispatchers.Main
    )
    private val startOffset = 0
    private val startLimit = 50
    private var offset = 0
    private var _sourceCoins = MutableLiveData<Array<Coin>>()
    private var _sourceColour = MutableLiveData<String>()
    private var _coins = MutableLiveData<Array<Coin>>()
    val coins
        get() = _coins

    private var _sourceTotalCoins = MutableLiveData<Int>()
    private var _volume = MutableLiveData<Long>()

    private var _totalCoins = MutableLiveData<Int>()
    val totalCoin
        get() = _totalCoins

    private val _status = MutableLiveData<CoinApiStatus>()
    val status: LiveData<CoinApiStatus>
        get() = _status

    init {
        getCoin()
    }

    fun getCoin() {
        coroutineScope.launch {
            var getPropertiesDeferred =
                CoinApi.retrofitService.getPropertiesAsync(startOffset, startLimit)
            try {
                _status.value =
                    CoinApiStatus.LOADING
                var result = getPropertiesDeferred.await()
                _sourceCoins.value = result.data.coins
                _sourceTotalCoins.value = result.data.stats.total
                _totalCoins.value = _sourceTotalCoins.value
                _coins.value = _sourceCoins.value
                _status.value =
                    CoinApiStatus.DONE
                offset = 50
            } catch (e: Exception) {
                _status.value =
                    CoinApiStatus.ERROR
            }
        }
    }


    override fun onCleared() {
        super.onCleared()
        viewModelJob.cancel()
    }

}