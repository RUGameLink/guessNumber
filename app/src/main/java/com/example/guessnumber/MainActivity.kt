package com.example.guessnumber

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast

class MainActivity : AppCompatActivity() {
    private lateinit var maxCountText: EditText
    private lateinit var minCountText: EditText
    private lateinit var startButton: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        init()

        startButton.setOnClickListener(startListener)
    }

    private var startListener: View.OnClickListener = View.OnClickListener {
        if(minCountText.text.isEmpty() || maxCountText.text.isEmpty()){
            Toast.makeText(applicationContext, "Заполните поля для старта!", Toast.LENGTH_SHORT).show()
        }
        else {
            var minCount = minCountText.text.toString()
            var maxCount = maxCountText.text.toString()
            if(maxCount.toInt() <= minCount.toInt()){
                Toast.makeText(applicationContext, "Числа должны быть неодинаковыми, минимальное значение меньше максимального!", Toast.LENGTH_SHORT).show()
            }
            else{
                Toast.makeText(applicationContext, "Все готово, начинаем", Toast.LENGTH_SHORT).show()

                val i = Intent(this@MainActivity, GameActivity::class.java)
                i.putExtra("minCount", minCount)
                i.putExtra("maxCount", maxCount)
                startActivity(i)
            }
        }
    }

    private fun init(){
        maxCountText = findViewById(R.id.maxNumText)
        minCountText = findViewById(R.id.minNumText)
        startButton = findViewById(R.id.startButton)
    }
}