package com.plcoding.cryptocurrencyappyt.data.repository

import android.util.Log
import com.plcoding.cryptocurrencyappyt.data.remote.CoinPaprikaApi
import com.plcoding.cryptocurrencyappyt.data.remote.dto.CoinDetailDto
import com.plcoding.cryptocurrencyappyt.data.remote.dto.CoinDto
import com.plcoding.cryptocurrencyappyt.domain.model.Coin
import com.plcoding.cryptocurrencyappyt.domain.model.CoinDetail
import com.plcoding.cryptocurrencyappyt.domain.repository.CoinRepository
import javax.inject.Inject

class CoinRepositoryImpl @Inject constructor(
    private val api: CoinPaprikaApi
) : CoinRepository {
    override suspend fun getCoins(): List<Coin> {
        return api.getCoins().map { toCoin(it) }
    }

    override suspend fun getCoinById(coinId: String): CoinDetail {
        return toCoinDetail(api.getCoinById(coinId))
    }

    private fun toCoinDetail(coinDetailDto: CoinDetailDto): CoinDetail {
        return CoinDetail(
            coinId = coinDetailDto.id,
            name = coinDetailDto.name,
            description = coinDetailDto.description,
            symbol = coinDetailDto.symbol,
            rank = coinDetailDto.rank,
            isActive = coinDetailDto.isActive,
            tags = coinDetailDto.tags.map { it.name },
            team = coinDetailDto.team
        )
    }

    private fun toCoin(coinDto: CoinDto): Coin {
        return Coin(
            id = coinDto.id,
            isActive = coinDto.isActive,
            name = coinDto.name,
            rank = coinDto.rank,
            symbol = coinDto.symbol,
        )
    }
}