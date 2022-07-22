package com.tan.weather.Models

import com.google.gson.annotations.SerializedName
import com.google.gson.annotations.Expose

class Location {
	@SerializedName("name")
	@Expose
	var name: String? = null

	@SerializedName("country")
	@Expose
	var country: String? = null

	@SerializedName("region")
	@Expose
	var region: String? = null

	@SerializedName("lat")
	@Expose
	var lat: String? = null

	@SerializedName("lon")
	@Expose
	var lon: String? = null

	@SerializedName("timezone_id")
	@Expose
	var timezoneId: String? = null

	@SerializedName("localtime")
	@Expose
	var localtime: String? = null

	@SerializedName("localtime_epoch")
	@Expose
	var localtimeEpoch: Int? = null

	@SerializedName("utc_offset")
	@Expose
	var utcOffset: String? = null
}