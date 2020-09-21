package com.example.iot_android.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [WeatherDB::class], version = 3)
abstract class DataBase: RoomDatabase() {

    abstract fun dao(): Dao

    companion object{
        private var INSTANCE: DataBase? = null

        fun getInstance(context: Context): DataBase?{
            if(INSTANCE == null)
            {
                synchronized(WeatherDB::class.java){
                    INSTANCE = Room.databaseBuilder(context.applicationContext,
                        DataBase::class.java,"weather.db")
                        .fallbackToDestructiveMigration()
                        .allowMainThreadQueries()
                        .build()
                }
            }
            return INSTANCE
        }

        fun destroyInstance(){
            INSTANCE = null
        }
    }
}