package com.example.instabloodnow.fragments

import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.instabloodnow.R

class sendRequest : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.donnee_list)

        val btn : Button = findViewById(R.id.sendDonneeRequest)
        btn.setOnClickListener {
            Toast.makeText(this, "Hellooo", Toast.LENGTH_SHORT).show()
        }

    }

}