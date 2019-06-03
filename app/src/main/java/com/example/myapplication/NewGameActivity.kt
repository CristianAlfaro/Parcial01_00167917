package com.example.myapplication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import com.example.myapplication.ViewModel.GameViewModel


class NewGameActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_new_game)


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
            Toast.makeText(this, "hola", Toast.LENGTH_SHORT).show()
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

}
