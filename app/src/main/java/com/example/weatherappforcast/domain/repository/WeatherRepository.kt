package com.example.weatherappforcast.domain.repository

import com.example.weatherappforcast.domain.utils.Resource
import com.example.weatherappforcast.domain.weather.WeatherInfo

interface WeatherRepository {
    suspend fun getWeatherData(lat:Double,long:Double):Resource<WeatherInfo>
}