package com.example.listview

import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.ListView
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        val list = findViewById<ListView>(R.id.list_view)
        val task = arrayListOf<String>()
        task.add("Make a ListView app")
        task.add("make Notes for Exam")
        task.add("Watch some tutorials")
        task.add("Read documentation of android")
        task.add("practice")
        task.add("practicee")
        task.add("practice")
        task.add("Like")
        task.add("Share to yoour lazy friends like me")
        task.add("comments your favourite tutor for android")
        task.add("Read documentation of android")
        task.add("Read documentation of android")
        task.add("practice")
        task.add("practicee")
        task.add("practice")
        task.add("Like")

        val adapterofmylist = ArrayAdapter(this,android.R.layout.simple_list_item_1,task)
        list.adapter = adapterofmylist
        list.setOnItemClickListener { parent, view, position, id ->
            val text = "Clicked on item : " +(view as TextView).text.toString()
            Toast.makeText(this,text,Toast.LENGTH_SHORT).show()

        }




    }
}