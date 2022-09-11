package com.example.weatherappforcast.presentation

import android.location.Location
import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.weatherappforcast.domain.location.LocationTracker
import com.example.weatherappforcast.domain.repository.WeatherRepository
import com.example.weatherappforcast.domain.utils.Resource
import com.example.weatherappforcast.domain.weather_state.WeatherState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class WeatherViewModel @Inject constructor(
    private val weatherRepository: WeatherRepository,
    private val locationTracker: LocationTracker
):ViewModel(){

    var state by mutableStateOf(WeatherState())
    private set


    fun loadWeatherInfo(){
        viewModelScope.launch {
            state = state.copy(
                isLoading = true,
                error = null
            )
            locationTracker.getCurrentLocation()?.let {
                getWeatherData(it)
            }?: kotlin.run {
                state = state.copy(
                    weatherInfo = null,
                    isLoading = false,
                    error = "Please Enable Your Location first"
                )
            }
        }
    }
    private suspend fun getWeatherData(location: Location){
        when(val result = weatherRepository.getWeatherData(location.latitude,location.longitude)){
            is  Resource.Success->{
               state= state.copy(
                    weatherInfo = result.data,
                    isLoading = false,
                    error = null
                )
                Log.d("jejeje", "getWeatherData: Success ${state.weatherInfo?.currentWeatherData}")

            }
            is  Resource.Error-> {
                Log.d("jejeje", "getWeatherData:Error ${result.data}")

                state= state.copy(
                    weatherInfo = null,
                    isLoading = false,
                    error = result.message
                )
            }
        }
    }
}