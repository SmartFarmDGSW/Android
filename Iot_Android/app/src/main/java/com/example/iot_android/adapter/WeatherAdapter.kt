package com.example.iot_android.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.iot_android.R
import com.example.iot_android.model.weather.Daily
import com.example.iot_android.model.weather.WeatherData
import com.example.iot_android.room.WeatherDB
import com.example.iot_android.service.utils.DateManager
import java.time.Instant
import java.time.LocalDateTime
import java.util.*
import kotlin.collections.ArrayList

class WeatherAdapter(val weatherList: WeatherDB) : RecyclerView.Adapter<WeatherAdapter.Holder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.weather_item, parent, false)
        return Holder(view)
    }

    override fun getItemCount(): Int {
        return weatherList.daily!!.size - 1
    }

    override fun onBindViewHolder(holder: Holder, position: Int) {
        holder.bind(weatherList.daily?.get(position + 1)!!)
    }

    inner class Holder(itemView: View) : RecyclerView.ViewHolder(itemView){

        val temp = itemView.findViewById<TextView>(R.id.textView38)
        val day = itemView.findViewById<TextView>(R.id.textView40)
        fun bind(weather: Daily) {
            temp.text = weather.temp.day.toInt().toString()
            day.text = DateManager.getDate(LocalDateTime.ofInstant(Instant.ofEpochMilli(weather.dt * 1000), TimeZone.getDefault().toZoneId()))
        }
    }
}