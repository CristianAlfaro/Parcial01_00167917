package com.example.myapplication.Fragment

import android.content.Context
import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.myapplication.Adapter.GameAdapter
import com.example.myapplication.R
import com.example.myapplication.ViewModel.GameViewModel
import com.google.android.material.floatingactionbutton.FloatingActionButton
import kotlinx.android.synthetic.main.content_main.view.*

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

private lateinit var gameViewModel: GameViewModel

class MainFragment : Fragment() {
    private var param1: String? = null
    private var param2: String? = null
    private var listener: OnFragmentInteractionListener? = null

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

    fun initAll(view: View){


        val recyclerView = view.recyclerview
        val adapter = object : GameAdapter(view.context){
            override fun addListener(holder: GameViewHolder, team1: String, team2: String, point1: Int, point2: Int) {
                holder.game_container.setOnClickListener {
                    /*
                    val bookFragment = BookFragment.newInstance(titulo, autor, favorito, caratula,editorial, resumen, tags)
                    fragmentManager!!.beginTransaction().replace(R.id.main_fragment_content,bookFragment).addToBackStack("").commit()
                    */
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

    }
}
