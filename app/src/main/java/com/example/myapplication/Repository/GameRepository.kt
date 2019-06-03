package com.example.myapplication.Repository

import androidx.lifecycle.LiveData
import com.example.myapplication.DAO.GameDao
import com.example.myapplication.Entities.Game

class GameRepository (private val gameDao: GameDao) {

    val allGame: LiveData<List<Game>> = gameDao.getGames()

    suspend fun insert(game: Game) {
        gameDao.insert(game)
    }

    suspend fun update(game: Game){
        gameDao.update(game)
    }
}