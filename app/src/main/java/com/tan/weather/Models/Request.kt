package com.tan.weather.Models

import com.google.gson.annotations.SerializedName
import com.google.gson.annotations.Expose

class Request {
	@SerializedName("type")
	@Expose
	var type: String? = null

	@SerializedName("query")
	@Expose
	var query: String? = null

	@SerializedName("language")
	@Expose
	var language: String? = null

	@SerializedName("unit")
	@Expose
	var unit: String? = null
}