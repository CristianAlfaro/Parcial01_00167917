package com.example.myapplication.Fragment

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.myapplication.Adapter.GameAdapter
import com.example.myapplication.Entities.Game
import com.example.myapplication.NewGameActivity
import com.example.myapplication.R
import com.example.myapplication.ViewModel.GameViewModel
import com.google.android.material.floatingactionbutton.FloatingActionButton
import kotlinx.android.synthetic.main.content_main.view.*

private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"


class MainFragment : Fragment(), GameFragment.OnFragmentInteractionListener {
    override fun onFragmentInteraction(uri: Uri) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    private var param1: String? = null
    private var param2: String? = null
    private var listener: OnFragmentInteractionListener? = null
    private lateinit var gameViewModel: GameViewModel
    private val newGameActivityRequestCode = 1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }

    }

    override fun onCreateView(


        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        var view =  inflater.inflate(R.layout.fragment_main, container, false)
        initAll(view)
        return view
    }



    fun onButtonPressed(uri: Uri) {
        listener?.onFragmentInteraction(uri)
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is OnFragmentInteractionListener) {
            listener = context
        } else {
            throw RuntimeException(context.toString() + " must implement OnFragmentInteractionListener")
        }
    }

    override fun onDetach() {
        super.onDetach()
        listener = null
    }

    interface OnFragmentInteractionListener {

        fun onFragmentInteraction(uri: Uri)
    }

    companion object {
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            MainFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }

    fun initAll(view: View) {


        val recyclerView = view.recyclerview
        val adapter = object : GameAdapter(view.context) {
            override fun addListener(holder: GameViewHolder,id: Int, team1: String, team2: String, point1: Int, point2: Int)  {
                holder.game_container.setOnClickListener {
                    val gameFragment = GameFragment.newInstance(id,team1,team2,point1,point2)
                    fragmentManager!!.beginTransaction().replace(R.id.fragment,gameFragment).addToBackStack("").commit()
                }

            }
        }
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(view.context)


        gameViewModel = ViewModelProviders.of(this).get(GameViewModel::class.java)

        gameViewModel.allGame.observe(this, Observer { words ->
            // Update the cached copy of the words in the adapter.
            words?.let { adapter.setGames(it) }
        })

        val fab: FloatingActionButton = view.findViewById(R.id.fab)
        fab.setOnClickListener {
            val intent = Intent(context, NewGameActivity::class.java)
            startActivityForResult(intent, newGameActivityRequestCode)


        }


    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, intentData: Intent?) {
        super.onActivityResult(requestCode, resultCode, intentData)

        if (requestCode == newGameActivityRequestCode && resultCode == Activity.RESULT_OK) {
            intentData?.let { data ->
                val game = Game(
                    data.getIntExtra(NewGameActivity.ID,0),
                    data.getStringExtra(NewGameActivity.TEAM1),
                    data.getStringExtra(NewGameActivity.TEAM2),
                    data.getIntExtra(NewGameActivity.POINT1,0),
                    data.getIntExtra(NewGameActivity.POINT2,0)
                )
                gameViewModel.insert(game)
            }
        } else {
            Toast.makeText(context, "No habian datos suficientes",Toast.LENGTH_LONG).show()
        }
    }


}
