package me.henryfbp.myapplication

import android.app.Activity
import android.os.Bundle
import android.util.Log
import android.widget.ArrayAdapter
import android.widget.ListView

class TempPicker : Activity() {

    lateinit var listView: ListView

    var wkTemps: ArrayList<String> = arrayListOf("1", "-10", "0", "30", "10")


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_temp_picker)

        var listAdapter = ArrayAdapter<String>(this, R.layout.simplerow, wkTemps) // create arrayAdapter
        listView = findViewById(R.id.listView)

        listView.adapter = listAdapter // Populate listView with arrayAdapter's content
    }


}
