package com.example.weatherappforcast.data.repository

import android.os.Build
import androidx.annotation.RequiresApi
import com.example.weatherappforcast.data.mappers.toWeatherInfo
import com.example.weatherappforcast.data.remote.WeatherApi
import com.example.weatherappforcast.data.remote.WeatherDto
import com.example.weatherappforcast.domain.repository.WeatherRepository
import com.example.weatherappforcast.domain.utils.Resource
import com.example.weatherappforcast.domain.weather.WeatherInfo
import javax.inject.Inject

class WeatherRepositoryImp @Inject constructor(
    private val weatherApi: WeatherApi

):WeatherRepository {

    @RequiresApi(Build.VERSION_CODES.O)
    override suspend fun getWeatherData(lat: Double, long: Double): Resource<WeatherInfo> {
        return try {
            Resource.Success(
                data = weatherApi.getWeatherData(lat,long).toWeatherInfo()
            )
        }catch (e:Exception){
            Resource.Error(message = e.message?:"Sorry for inconvenience", data = null)
        }
        }
    }