package com.merttoptas.bitcointracker.views.model

data class CoinModel(
    val status: String,
    val data: data
)

data class data(
    val stats: Stats,
    val coins: Array<Coin>
)

data class Coin(
    val id: Long,
    val uuid: String,
    val slug: String,
    val symbol: String,
    val name: String,
    val description: String?,
    val color: String?,
    val price: String?,
    val iconUrl: String,
    val rank: Int
)

data class Stats(
    val total: Int
)