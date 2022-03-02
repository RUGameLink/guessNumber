package com.example.guessnumber

import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class GameActivity : AppCompatActivity()  {
    private lateinit var minCount: String
    private lateinit var maxCount: String
    lateinit var testText: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.game_activity)

        minCount= intent.getStringExtra("minCount").toString()
        maxCount= intent.getStringExtra("maxCount").toString()

        testText = findViewById(R.id.testText)

        testText.setText(minCount + " " + maxCount)
    }
}