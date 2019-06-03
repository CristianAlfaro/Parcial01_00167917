package com.example.myapplication.ViewModel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.example.myapplication.DataBase.GameDataBase
import com.example.myapplication.Entities.Game
import com.example.myapplication.Repository.GameRepository
import kotlinx.coroutines.launch

class GameViewModel(application: Application): AndroidViewModel(application) {
    private val gameRepository: GameRepository
    val allGame:LiveData<List<Game>>

    init {
        val gameDao = GameDataBase.getDatabase(application, viewModelScope).gameDao()
        gameRepository = GameRepository(gameDao)
        allGame = gameRepository.allGame
    }

    fun insert(game: Game) = viewModelScope.launch {
        gameRepository.insert(game)
    }
}