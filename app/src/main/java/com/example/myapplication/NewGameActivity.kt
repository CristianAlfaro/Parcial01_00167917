package com.example.myapplication

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import com.example.myapplication.ViewModel.GameViewModel
import kotlinx.android.synthetic.main.activity_new_game.*
import kotlinx.android.synthetic.main.recycle_view_item.*


class NewGameActivity : AppCompatActivity() {


    private lateinit var edteam1: EditText
    private lateinit var edteam2: EditText
    private lateinit var tvpoint1: TextView
    private lateinit var tvpoint2: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_new_game)

        edteam1 = findViewById(R.id.team1)
        edteam2 = findViewById(R.id.team2)
        tvpoint1 = findViewById(R.id.tv_marcador1)
        tvpoint2 = findViewById(R.id.tv_marcador2)


        val tv_t1 = findViewById<TextView>(R.id.tv_marcador1)
        val tv_t2 = findViewById<TextView>(R.id.tv_marcador2)
        val btn1_t1 = findViewById<TextView>(R.id.btn1_team1)
        val btn2_t1 = findViewById<TextView>(R.id.btn2_team1)
        val btn3_t1 = findViewById<TextView>(R.id.btn3_team1)
        val btn_t1 = findViewById<TextView>(R.id.btn_team1)
        val btn1_t2 = findViewById<TextView>(R.id.btn1_team2)
        val btn2_t2 = findViewById<TextView>(R.id.btn2_team2)
        val btn3_t2 = findViewById<TextView>(R.id.btn3_team2)
        val btn_t2 = findViewById<TextView>(R.id.btn_team2)
        val btn_save = findViewById<Button>(R.id.btn_save)

        btn_save.setOnClickListener { v ->
            save()
        }
        btn1_t1.setOnClickListener { v ->
            suma(tv_t1,1)
        }
        btn2_t1.setOnClickListener { v ->
            suma(tv_t1,2)
        }
        btn3_t1.setOnClickListener { v ->
            suma(tv_t1,3)
        }
        btn_t1.setOnClickListener { v ->
            Toast.makeText(this, "Se resto un punto ", Toast.LENGTH_SHORT).show()
            suma(tv_t1,-1)
        }
        btn1_t2.setOnClickListener { v ->
            suma(tv_t2,1)
        }
        btn2_t2.setOnClickListener { v ->
            suma(tv_t2,2)
        }
        btn3_t2.setOnClickListener { v ->
            suma(tv_t2,3)
        }
        btn_t2.setOnClickListener { v ->
            Toast.makeText(this, "Se resto un punto", Toast.LENGTH_SHORT).show()
            suma(tv_t2,-1)
        }


        }
    fun suma (tv: TextView, x: Int){
        var cont = Integer.parseInt(tv.getText().toString())
        cont += x
        tv.setText("" + cont)
    }

    fun save (){
        val replyIntent = Intent()
        if (TextUtils.isEmpty(team1.text) and TextUtils.isEmpty(team2.text)) {
            setResult(Activity.RESULT_CANCELED, replyIntent)
        }
        else {
            val team1 = edteam1.text.toString()
            val team2 = edteam2.text.toString()
            val point1 = Integer.parseInt(tvpoint1.text.toString())
            val point2 = Integer.parseInt(tvpoint2.text.toString())
            val id: Int = 0
            replyIntent.putExtra(TEAM1, team1)
            replyIntent.putExtra(TEAM2, team2)
            replyIntent.putExtra(POINT1, point1)
            replyIntent.putExtra(POINT2, point2)
            replyIntent.putExtra(ID, id)
            setResult(Activity.RESULT_OK, replyIntent)
            Toast.makeText(this, "Guardado con exito", Toast.LENGTH_SHORT).show()
        }
        finish()
    }
    companion object {
        const val TEAM1 = "team1"
        const val TEAM2 = "team2"
        const val POINT1 = "point1"
        const val POINT2= "point2"
        const val ID= "id"
    }
}
