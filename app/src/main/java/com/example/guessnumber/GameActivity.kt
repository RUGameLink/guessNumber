package com.example.guessnumber

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import kotlin.properties.Delegates

class GameActivity : AppCompatActivity()  {
    private lateinit var minCountText: String
    private lateinit var maxCountText: String

    private lateinit var biggerButton: Button
    private lateinit var lessButton: Button
    private lateinit var endButton: Button
    lateinit var gameText: TextView
    private lateinit var rangeText: TextView

    private var maxCount by Delegates.notNull<Int>()
    private var minCount by Delegates.notNull<Int>()
    private var midCount by Delegates.notNull<Int>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.game_activity)

        init()

        lessButton.setOnClickListener(lessListener)
        biggerButton.setOnClickListener(biggerListener)
        endButton.setOnClickListener(endListener)

        rangeText.setText("Ваш диапазон от ${minCountText} до ${maxCountText}")

        maxCount = maxCountText.toInt()
        minCount = minCountText.toInt()

        checkNumberBigger()
    }

    private fun checkNumberBigger(){
        midCount = (maxCount + minCount) / 2

        when(midCount){
            maxCount ->{
                midCount - 1
                gameText.setText("Я выиграл! Ваше число ${midCount}")
                endGame()
            }
            minCount ->{
                midCount + 1
                gameText.setText("Я выиграл! Ваше число ${midCount}")
                endGame()
            }
            else ->{
                gameText.setText("Ваше число ${midCount}?")
            }
        }

        Log.d("check", "min = " + minCount + " max = " + maxCount + " mid " + midCount);
    }

    private var lessListener: View.OnClickListener = View.OnClickListener {
        maxCount = midCount
        checkNumberBigger()
    }

    private var biggerListener: View.OnClickListener = View.OnClickListener {
        minCount = midCount
        checkNumberBigger()
    }

    private var endListener: View.OnClickListener = View.OnClickListener {
        endGame()
    }

    private fun init(){
        minCountText= intent.getStringExtra("minCount").toString()
        maxCountText= intent.getStringExtra("maxCount").toString()

        gameText = findViewById(R.id.gameText)
        rangeText = findViewById(R.id.rangeText)

        lessButton = findViewById(R.id.lessButton)
        biggerButton = findViewById(R.id.biggerButton)
        endButton = findViewById(R.id.endButton)
    }

    private fun restartGame(){
        val i = Intent(this, MainActivity::class.java)
        startActivity(i)
    }

    private fun endGame(){
        gameText.setText("Я выиграл! Ваше число ${midCount}")
        Toast.makeText(applicationContext, "Победа! Игра скоро перезапустится", Toast.LENGTH_LONG).show()

        val handler = Handler()
        handler.postDelayed({
            restartGame()
        }, 2000)
    }
}