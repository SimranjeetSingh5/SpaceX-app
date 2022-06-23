package com.example.spacexapp.database

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.spacexapp.models.Rocket
import kotlinx.coroutines.flow.Flow

//@Dao
//interface RocketDao {
//
//    @Query("select * from rockets_table")
//    fun getAllRockets(): LiveData<List<Rocket>>
//
//    @Insert(onConflict = OnConflictStrategy.REPLACE)
//    suspend fun insertRocketsToRoom(rockets:List<Rocket>):Long
//
//    @Delete
//    suspend fun deleteRocket(rocket: Rocket)
//}