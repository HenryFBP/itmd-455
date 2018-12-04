package me.henryfbp.final_app

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.View.INVISIBLE
import android.view.View.VISIBLE
import android.widget.Button
import android.widget.ProgressBar

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val buttonStart = findViewById<Button>(R.id.buttonStart)
        val buttonStop = findViewById<Button>(R.id.buttonStop)
        val progressBar = findViewById<ProgressBar>(R.id.progressBar)

        buttonStart.setOnClickListener {

            progressBar.visibility = VISIBLE

            progressBar.progress = 10
        }

        buttonStop.setOnClickListener {
            progressBar.progress = 0

            progressBar.visibility = INVISIBLE
        }
    }
}
