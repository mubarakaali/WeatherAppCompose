package com.example.weatherappforcast.presentation.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.weatherappforcast.R
import com.example.weatherappforcast.ui.theme.DeepBlue
import com.example.weatherappforcast.ui.theme.WeatherAppForcastTheme

@Composable
@Preview
fun WeatherShortInfoPreview() {
    WeatherAppForcastTheme {
        Row(
            modifier = Modifier
                .background(DeepBlue)
                .padding(10.dp)
        ) {
            WeatherShortInfo(
                icon = ImageVector.vectorResource(id = R.drawable.ic_pressure),
                name = "106",
                unit = "hps",
                tintColor = Color.White
            )
        }
    }
}


@Composable
fun WeatherShortInfo(
    icon: ImageVector,
    tintColor: Color = Color.White,
    name: String,
    unit: String
) {
    Row {
        Icon(
            imageVector = icon,
            contentDescription = null,
            modifier = Modifier.size(20.dp),
            tint = tintColor
        )
        Spacer(modifier = Modifier.width(4.dp))
        Text(
            text = name,
            color = Color.White
        )
        Spacer(modifier = Modifier.width(2.dp))
        Text(
            text = unit,
            color = Color.White
        )
    }
}