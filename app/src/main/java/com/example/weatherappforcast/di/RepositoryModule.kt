package com.example.weatherappforcast.di

import com.example.weatherappforcast.data.repository.WeatherRepositoryImp
import com.example.weatherappforcast.domain.repository.WeatherRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {
    @Singleton
    @Binds
    abstract fun providesWeatherRepository(weatherRepositoryImp: WeatherRepositoryImp):WeatherRepository
}