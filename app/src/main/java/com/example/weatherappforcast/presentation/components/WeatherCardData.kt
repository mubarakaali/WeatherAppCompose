package com.example.weatherappforcast.presentation.components

import android.os.Build
import android.util.Log
import androidx.annotation.RequiresApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.weatherappforcast.R
import com.example.weatherappforcast.domain.weather_state.WeatherState
import java.time.format.DateTimeFormatter
import kotlin.math.roundToInt

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun WeatherContent(
    weather: WeatherState,
    background: Color,
    modifier: Modifier = Modifier
) {
    Log.d("jejeje ", "WeatherContent:FIRST time....")
    weather.weatherInfo?.currentWeatherData?.let { weatherState ->
        Log.d(
            "jejeje ",
            "WeatherContent:weatherState..... ${weatherState.weatherType.weatherDesc} "
        )
        Card(
            backgroundColor = background,
            shape = RoundedCornerShape(10.dp),
            modifier = modifier.padding(16.dp)
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {

                Text(
                    text = "Today ${weatherState.time.format(DateTimeFormatter.ofPattern("HH:mm"))}",
                    color = Color.White,
                    modifier = Modifier
                        .align(Alignment.End)
                )
                Spacer(modifier = Modifier.height(16.dp))
                Image(
                    painter = painterResource(id = weatherState.weatherType.iconRes),
                    contentDescription = null,
                    modifier = Modifier.width(200.dp)
                )
                Spacer(modifier = Modifier.height(10.dp))
                Text(
                    text = "${weatherState.temperatureCelsius}°C",
                    fontSize = 50.sp,
                    color = Color.White
                )
                Spacer(modifier = Modifier.height(10.dp))
                Text(
                    text = weatherState.weatherType.weatherDesc,
                    fontSize = 20.sp,
                    color = Color.White
                )
                Spacer(modifier = Modifier.height(25.dp))
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceAround
                ) {
                    WeatherShortInfo(
                        icon = ImageVector.vectorResource(id = R.drawable.ic_pressure),
                        name = weatherState.pressure.roundToInt().toString(),
                        unit = "hps"
                    )
                    WeatherShortInfo(
                        icon = ImageVector.vectorResource(id = R.drawable.ic_drop),
                        name = weatherState.humidity.roundToInt().toString(),
                        unit = "%"
                    )
                    WeatherShortInfo(
                        icon = ImageVector.vectorResource(id = R.drawable.ic_wind),
                        name = weatherState.windSpeed.roundToInt().toString(),
                        unit = "km/h"
                    )
                }
            }
        }
    }
}
