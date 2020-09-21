package com.example.iot_android.room

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.iot_android.model.weather.Daily

@Entity(tableName = "Weather")
class WeatherDB(
    @PrimaryKey(autoGenerate = true) var id: Long,
    @ColumnInfo(name = "lat") var lat: Int?,
    @ColumnInfo(name = "lon") var lon: Int?,
    @ColumnInfo(name = "timezone") var timezone: String?,
    @ColumnInfo(name = "timezone_offset") var timezone_offset: Int?,
    @ColumnInfo(name = "daily") var daily: ArrayList<Daily>?
)