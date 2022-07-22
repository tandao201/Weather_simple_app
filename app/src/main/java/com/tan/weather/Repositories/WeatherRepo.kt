package com.tan.weather.Repositories

import com.tan.weather.API.RetrofitHelper
import com.tan.weather.API.WeatherApi
import com.tan.weather.Models.Weather
import retrofit2.Response

class WeatherRepo {

	val weatherApi = RetrofitHelper.getInstance().create(WeatherApi::class.java)

	suspend fun getWeatherByCity(city: String) :Response<Weather>{
		return weatherApi.getWeatherByCity("a0ab51d12d5b4dfeb9cba9095f7189cb",city)
	}
}