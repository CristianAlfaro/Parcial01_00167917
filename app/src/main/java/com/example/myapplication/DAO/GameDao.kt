package com.example.myapplication.DAO

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.myapplication.Entities.Game

@Dao
interface GameDao {
    @Query("SELECT * from game_table")
    fun getGames(): LiveData<List<Game>>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(game: Game)

    @Update
    fun update(game: Game)

}