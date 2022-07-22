package com.tan.weather.Models

import com.google.gson.annotations.SerializedName
import com.google.gson.annotations.Expose

class Weather {
	@SerializedName("request")
	@Expose
	var request: Request? = null

	@SerializedName("location")
	@Expose
	var location: Location? = null

	@SerializedName("current")
	@Expose
	var current: Current? = null
}