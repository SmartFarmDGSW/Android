package com.example.iot_android.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.iot_android.R
import com.example.iot_android.model.WeatherLayoutData

class WeatherAdapter : RecyclerView.Adapter<WeatherAdapter.Holder>() {

    lateinit var weatherList : ArrayList<WeatherLayoutData>

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.weather_item, parent, false)
        return Holder(view)
    }

    override fun getItemCount(): Int {
        return weatherList.size
    }

    override fun onBindViewHolder(holder: Holder, position: Int) {

        holder.bind(weatherList[position])
    }

     class Holder(itemView: View) : RecyclerView.ViewHolder(itemView){
        fun bind(weather: WeatherLayoutData) {

        }
     }
}