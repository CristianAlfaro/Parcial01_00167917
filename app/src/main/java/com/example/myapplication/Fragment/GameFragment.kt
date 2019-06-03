package com.example.myapplication.Fragment

import android.content.Context
import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.example.myapplication.R
import kotlinx.android.synthetic.main.fragment_game.view.*


private const val ID = "id"
private const val TEAM1 = "team1"
private const val TEAM2 = "team2"
private const val POINT1 = "point1"
private const val POINT2 = "point2"



class GameFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var id: Int? = null
    private var team1: String? = null
    private var team2: String? = null
    private var point1: Int? = null
    private var point2: Int? = null
    private var listener: OnFragmentInteractionListener? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            id = it.getInt(ID)
            team1 = it.getString(TEAM1)
            team2 = it.getString(TEAM2)
            point1 = it.getInt(POINT1)
            point2 = it.getInt(POINT2)

        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_game, container, false).apply {
            var id = arguments?.getInt("id")
            var team1 = arguments?.getString("team1")
            var team2 = arguments?.getString("team2")
            var point1 = arguments?.getInt("point1")
            var point2 = arguments?.getInt("point2")

            equipo1.text = team1
            equipo2.text = team2
            marcador1_tv.text = point1.toString()
            marcador2_tv.text = point2.toString()
        }
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
        fun newInstance(id:Int,team1: String, team2: String, point1: Int, point2: Int) =
            GameFragment().apply {
                arguments = Bundle().apply {
                    putInt(ID,id)
                    putString(TEAM1,team1)
                    putString(TEAM2,team2)
                    putInt(POINT1,point1)
                    putInt(POINT2,point2)
                }
            }
    }
}
