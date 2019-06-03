package com.example.myapplication.Entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "game_table")

data class Game (
    @PrimaryKey(autoGenerate = true) @ColumnInfo(name = "id" )val id: Int,
    @ColumnInfo (name = "Team1") val team1: String,
    @ColumnInfo (name = "Team2") val team2: String,
    @ColumnInfo (name = "Point1") val point1: Int,
    @ColumnInfo (name = "Point2") var point2: Int
)