package com.example.spacexapp.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import androidx.sqlite.db.SupportSQLiteDatabase
import com.example.spacexapp.models.Rocket
import javax.inject.Inject
import javax.inject.Provider

//@Database(entities = [Rocket::class],version=1,exportSchema = false)
//@TypeConverters(Converters::class)
//abstract class RocketDatabase : RoomDatabase() {
//    abstract fun getRocketDao(): RocketDao

//}