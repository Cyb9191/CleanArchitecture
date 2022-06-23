package com.example.network4

import android.app.Application
import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil.setContentView
import androidx.lifecycle.Observer
import com.example.network4.network.dto.RepoDto

class AirQualitySearachScreen : AppCompatActivity(){


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        setContentView(R.layout.activity_main)
        val firsttext = findViewById(R.id.textview_first) as TextView
        val errortext=findViewById(R.id.textview_error) as TextView

        var viewModel: MainActivityViewModel =
            (application as MyApplication).mainActivityViewModelStarter.create(MainActivityViewModel::class.java)

        //ViewModelProvider(this).get(MainActivityViewModel::class.java)


        val wheatherObserver = Observer<RepoDto> { newWheater ->
            firsttext.text = newWheater.city_name + "\n\n" + newWheater.lon.toString() +
                    "\n\n" + newWheater.timezone + "\n\n" + newWheater.lat.toString() + "\n\n" +
                    newWheater.country_code + "\n\n" + newWheater.state_code + "\n\n" + newWheater.data[0].mold_level +
                    "\n\n" + newWheater.data[0].aqi + "\n\n" + newWheater.data[0].pm10 + "\n\n" + newWheater.data[0].co +
                    "\n\n" + newWheater.data[0].o3 + "\n\n" + newWheater.data[0].predominant_pollen_type +
                    "\n\n" + newWheater.data[0].so2 + "\n\n" + newWheater.data[0].pollen_level_tree + "\n\n" +
                    newWheater.data[0].pollen_level_weed + "\n\n" + newWheater.data[0].no2 +
                    "\n\n" + newWheater.data[0].pm25 + "\n\n" + newWheater.data[0].pollen_level_grass
            errortext.text = "No Error"
        }

        val errorObserver= Observer<Exception> { newError ->
            errortext.text = newError.toString()
            firsttext.text ="No Data"
        }
        viewModel.listWeather.observe(this, wheatherObserver)
        viewModel.listError.observe(this, errorObserver)

    }}