package com.tan.weather

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.drawable.BitmapDrawable
import android.os.AsyncTask
import android.os.Bundle
import android.util.Log
import android.view.MotionEvent
import android.view.View
import android.widget.SearchView
import androidx.appcompat.app.AppCompatActivity
import com.tan.weather.Models.Weather
import com.tan.weather.Repositories.WeatherRepo
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.*
import kotlin.coroutines.CoroutineContext
import kotlin.math.absoluteValue


class MainActivity() : AppCompatActivity(), CoroutineScope {
	override val coroutineContext: CoroutineContext
		get() = Job()
	val TAG = "MainActivity"
	val weatherRepo = WeatherRepo()
	lateinit var weather: Weather
	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
		setContentView(R.layout.activity_main)
		searchCity("Ha noi")
		searchViewCity.setOnQueryTextListener(object : SearchView.OnQueryTextListener,
			androidx.appcompat.widget.SearchView.OnQueryTextListener {
			override fun onQueryTextSubmit(query: String?): Boolean {
				searchCity(query!!)
				searchViewCity.clearFocus()
				return true
			}

			override fun onQueryTextChange(newText: String?): Boolean {
				return false
			}
		})

		relativeLayoutWrapper.setOnTouchListener(object : View.OnTouchListener {
			override fun onTouch(p0: View?, p1: MotionEvent?): Boolean {
				searchViewCity.clearFocus()
				return false
			}
		})
	}

	private fun searchCity(city: String) {
		launch(Dispatchers.IO) {
			withContext(Dispatchers.Main) {
				progress_circular.visibility = View.VISIBLE
				relativeLayoutContent.visibility = View.GONE
			}

			weather = getData(city)!!

			if (weather != null) {
				withContext(Dispatchers.Main) {
					bindData(weather)
				}
			}
			withContext(Dispatchers.Main) {
				progress_circular.visibility = View.GONE
				relativeLayoutContent.visibility = View.VISIBLE
			}
		}
	}

	private fun resizeImage(img: Int): Bitmap {
		val d = BitmapFactory.decodeResource(resources, img)
		val nh = (d.height * (512.0 / d.width)).toInt()
		val scaled = Bitmap.createScaledBitmap(d, 512, nh, true)
		return scaled
	}

	private fun bindData(weather: Weather) {

		if (weather.current?.isDay == "yes") {
			var bitmap = BitmapDrawable(resizeImage(R.drawable.day_image))
			relativeLayoutMain.setBackgroundDrawable(bitmap)
		} else {
			var bitmap = BitmapDrawable(resizeImage(R.drawable.night_image))
			relativeLayoutMain.setBackgroundDrawable(bitmap)
		}

		logInfoWeather(weather)
		textViewCityName.text = weather.location?.name + ", "
		textViewCountryName.text = weather.location?.country!!
		textViewTime.text = weather.location?.localtime
		textViewTemp.text = weather.current?.temperature.toString() + "\u2103"
		textViewState.text = weather.current?.weatherDescriptions?.get(0)
		textViewValueView.text = weather.current?.visibility.toString() + " km"
		textViewValueWind.text = weather.current?.windSpeed.toString() + " m/s"
		textViewValueHumid.text = weather.current?.humidity.toString() + " %"
	}

	private fun logInfoWeather(weather: Weather) {
		Log.e("Weather info:", weather.location?.name.toString())
		Log.e("Weather info:", weather.location?.country!!)
		Log.e("Weather info:", weather.location?.localtime.toString())
		Log.e("Weather info:", weather.current?.temperature.toString() + "\u2103")
		Log.e("Weather info:", weather.current?.weatherDescriptions?.get(0).toString())
		Log.e("Weather info:", weather.current?.visibility.toString() + " km")
		Log.e("Weather info:", weather.current?.windSpeed.toString() + " m/s")
		Log.e("Weather info:", weather.current?.humidity.toString() + " %")
	}

	private suspend fun getData(city: String): Weather? {
		var response = weatherRepo.getWeatherByCity(city)
		return if (response.isSuccessful) {
			response.body()
		} else {
			null
		}
	}

}