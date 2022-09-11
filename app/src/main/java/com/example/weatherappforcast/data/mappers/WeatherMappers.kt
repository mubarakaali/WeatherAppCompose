package com.example.weatherappforcast.data.mappers

import android.os.Build
import android.util.Log
import androidx.annotation.RequiresApi
import com.example.weatherappforcast.data.remote.WeatherDataDto
import com.example.weatherappforcast.data.remote.WeatherDto
import com.example.weatherappforcast.domain.weather.WeatherData
import com.example.weatherappforcast.domain.weather.WeatherInfo
import com.example.weatherappforcast.domain.weather.WeatherType
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter


data class WeatherDataIndex(
    val index: Int,
    val weatherData: WeatherData
)

@RequiresApi(Build.VERSION_CODES.O)
fun WeatherDataDto.toWeatherDataMap(): Map<Int, List<WeatherData>> {
    return time.mapIndexed { index, time ->
        val temperature = temperatures[index]
        val weatherCode = weatherCodes[index]
        val windSpeed = windSpeeds[index]
        val pressure = pressures[index]
        val humidity = humidities[index]

        WeatherDataIndex(
            index = index,
            weatherData = WeatherData(
                time = LocalDateTime.parse(time, DateTimeFormatter.ISO_DATE_TIME),
                temperatureCelsius = temperature,
                pressure = pressure,
                windSpeed = windSpeed,
                humidity = humidity,
                weatherType = WeatherType.fromWeatherType(weatherCode)
            )
        )
    }.groupBy {
        it.index / 24
        Log.d("jejeje ", "WeatherMappers toWeatherDataMap() groupBy  :.... ${ it.index / 24}")
    }.mapValues { mapData ->
        Log.d("jejeje ", "WeatherMappers toWeatherDataMap() mapValues :.... ${ mapData.value.map { it.weatherData }}")
        mapData.value.map { it.weatherData }
    }
}

@RequiresApi(Build.VERSION_CODES.O)
fun WeatherDto.toWeatherInfo(): WeatherInfo {
    val weatherDataMap = weatherData.toWeatherDataMap()
    Log.d("jejeje ", "WeatherMappers toWeatherInfo: weatherDataMap ${weatherDataMap}} ")
    val currentTime = LocalDateTime.now()
    val currentWeatherData = weatherDataMap[1]?.find {
        val hour = if (currentTime.minute < 30) currentTime.hour else currentTime.hour + 1
        it.time.hour == hour
    }
    Log.d("jejeje ", "WeatherMappers toWeatherInfo: currentWeatherData ${currentWeatherData}} ")
    return WeatherInfo(
        weatherDataPerDay = weatherDataMap,
        currentWeatherData = currentWeatherData
    )
}
