package com.example.weatherappforcast.domain.weather

data class WeatherInfo(
    val weatherDataPerDay:Map<Int,List<WeatherData>>,
    val currentWeatherData:WeatherData?
)