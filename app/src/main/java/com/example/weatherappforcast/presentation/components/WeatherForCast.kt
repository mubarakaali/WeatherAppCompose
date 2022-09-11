package com.example.weatherappforcast.presentation.components

import android.os.Build
import android.util.Log
import androidx.annotation.RequiresApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.Icon
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.weatherappforcast.R
import com.example.weatherappforcast.domain.weather.WeatherData
import com.example.weatherappforcast.domain.weather_state.WeatherState
import com.example.weatherappforcast.ui.theme.DeepBlue
import com.example.weatherappforcast.ui.theme.WeatherAppForcastTheme
import java.time.format.DateTimeFormatter

@Composable
@Preview
fun WeatherForCastPreview() {
    WeatherAppForcastTheme {
        Surface(modifier = Modifier.background(DeepBlue)) {
        }
//        WeatherForCast()
    }
}

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun WeatherCollection(weatherData: WeatherState){
    weatherData.weatherInfo?.weatherDataPerDay?.get(1)?.let {weatherData->
            Text(
                text = "Today", color = Color.White)
            LazyRow(content = {
                itemsIndexed(weatherData){ index,weatherItem->
                    Log.d("jejeje ", "Index $index  WeatherCollection:... ${weatherItem.time} ")
                    if (index <= 23)
                    WeatherForCastHourly(weatherData = weatherItem)
                }
            })
    }
}

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun WeatherForCastHourly(
    weatherData: WeatherData,
    color: Color = Color.White,
) {
    val formattedHour = remember(weatherData){
        weatherData.time.format(DateTimeFormatter.ofPattern("HH:mm"))
    }

    Column(
        modifier = Modifier.padding(10.dp),
        verticalArrangement = Arrangement.spacedBy(5.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = formattedHour,
            color = color,
            fontSize = 12.sp
        )
        Image(
             painter = painterResource(id = weatherData.weatherType.iconRes),
             modifier = Modifier.size(20.dp),
             contentDescription = "icon-icon"
        )
        Text(
            text = "${weatherData.temperatureCelsius} 'C",
            color = color,
            fontSize = 14.sp,
            fontWeight = FontWeight.Bold
        )
    }
}