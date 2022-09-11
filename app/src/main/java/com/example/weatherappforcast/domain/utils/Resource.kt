package com.example.weatherappforcast.domain.utils

sealed class Resource<T>(val data:T?=null,val message:String?=null){
    class Success<T>(data: T?):Resource<T>(data = data)
    class Error<T>(message: String?,data: T?):Resource<T>(data = data, message = message)
}
