package com.tan.weather.API

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitHelper {

	val BASE_URL = "http://api.weatherstack.com/"

	fun getInstance() :Retrofit {
		return Retrofit.Builder().baseUrl(BASE_URL)
			.addConverterFactory(GsonConverterFactory.create())
			.build()
	}
}