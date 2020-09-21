package com.example.iot_android.room

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface Dao {
    @Query("SELECT * FROM weather")
    fun getAll(): WeatherDB

    @Query("DELETE FROM weather")
    fun delete()

    @Insert
    fun insert(weatherDB: WeatherDB)
}