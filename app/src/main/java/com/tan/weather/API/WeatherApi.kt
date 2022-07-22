package com.tan.weather.API

import com.tan.weather.Models.Weather
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface WeatherApi {

	@GET("current")
	suspend fun getWeatherByCity(@Query("access_key") key:String, @Query("query") city:String) :Response<Weather>
}