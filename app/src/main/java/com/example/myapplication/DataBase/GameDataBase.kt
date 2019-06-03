package com.example.myapplication.DataBase

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import com.example.myapplication.DAO.GameDao
import com.example.myapplication.Entities.Game
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch


@Database(entities = [Game::class], version = 5)
abstract class GameDataBase : RoomDatabase() {
    abstract fun gameDao(): GameDao

    companion object {
        @Volatile
        private var INSTANCE: GameDataBase? = null

        fun getDatabase(
            context: Context,
            scope: CoroutineScope
        ): GameDataBase {
            return INSTANCE?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    GameDataBase::class.java,
                    "word_database"
                )
                    .fallbackToDestructiveMigration()
                    .addCallback(WordDatabaseCallback(scope))
                    .build()
                INSTANCE = instance
                instance
            }
        }

        private class WordDatabaseCallback(
            private val scope: CoroutineScope
        ) : RoomDatabase.Callback() {
            override fun onOpen(db: SupportSQLiteDatabase) {
                super.onOpen(db)
                // If you want to keep the data through app restarts,
                // comment out the following line.
                INSTANCE?.let { database ->
                    scope.launch {
                        //populateDatabase(database.gameDao())
                    }
                }
            }
        }

        suspend fun populateDatabase(gameDao: GameDao) {

            val True:Boolean = true
            val False:Boolean = false

            var game = Game( 0, "Lakers", "Warrios", 130, 120)
            gameDao.insert(game)

            game = Game( 0, "Lakers", "Warrios", 80, 40)
            gameDao.insert(game)

            game = Game( 0, "Lakers", "Warrios", 40, 100)
            gameDao.insert(game)

            game = Game( 0, "Lakers", "Warrios", 70, 75)
            gameDao.insert(game)

            game = Game( 0, "Lakers", "Warrios", 130, 110)
            gameDao.insert(game)


        }
    }


}