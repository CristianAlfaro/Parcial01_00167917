package com.example.myapplication.Adapter

/*
 * Copyright (C) 2017 Google Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

import android.annotation.SuppressLint
import android.content.Context
import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import com.example.myapplication.Entities.Game
import com.example.myapplication.R


abstract class GameAdapter internal constructor(
    context: Context
) : RecyclerView.Adapter<GameAdapter.GameViewHolder>() {

    abstract fun addListener(holder: GameViewHolder, team1: String, team2: String, point1: Int, point2: Int)

    private val inflater: LayoutInflater = LayoutInflater.from(context)
    private var games = emptyList<Game>() // Cached copy of words

    inner class GameViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val team1: TextView = itemView.findViewById(R.id.tv_team1)
        val team2: TextView = itemView.findViewById(R.id.tv_team2)
        val point1: TextView = itemView.findViewById(R.id.tv_point1)
        val point2: TextView = itemView.findViewById(R.id.tv_point2)
        val game_container:LinearLayout = itemView.findViewById(R.id.item_view)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GameViewHolder {
        val itemView = inflater.inflate(R.layout.recycle_view_item, parent, false)
        return GameViewHolder(itemView)
    }

    @SuppressLint("ResourceAsColor")
    override fun onBindViewHolder(holder: GameViewHolder, position: Int) {
        val current = games[position]
        holder.team1.text = current.team1
        holder.team2.text = current.team2
        holder.point1.text = current.point1.toString()
        holder.point2.text = current.point2.toString()

        addListener(holder,current.team1,current.team2, current.point1, current.point2)

    }

    internal fun setGames(games: List<Game>) {
        this.games = games
        notifyDataSetChanged()
    }


    override fun getItemCount() = games.size
}


