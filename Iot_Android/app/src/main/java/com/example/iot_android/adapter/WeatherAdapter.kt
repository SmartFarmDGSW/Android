package com.example.iot_android.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.iot_android.R
import com.example.iot_android.model.weather.WeatherData

class WeatherAdapter(val weatherList : ArrayList<WeatherData>) : RecyclerView.Adapter<WeatherAdapter.Holder>() {

    var count = 1

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

    inner class Holder(itemView: View) : RecyclerView.ViewHolder(itemView){

        val temp = itemView.findViewById<TextView>(R.id.textView38)

        fun bind(weather: WeatherData) {
            if(count < 8) {
                temp.text = weather.daily.get(count++).toString()
            }
        }
    }
}