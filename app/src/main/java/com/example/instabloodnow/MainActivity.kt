package com.example.instabloodnow

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        supportActionBar?.show()

        val createBtn : Button = findViewById(R.id.createAccount)
        val signIn : Button = findViewById(R.id.signin)

        createBtn.setOnClickListener {
            val intent = Intent(this@MainActivity, Registration::class.java)
            startActivity(intent)
        }

        signIn.setOnClickListener {
            val intent = Intent(this@MainActivity, SignIn::class.java)
            startActivity(intent)
        }
    }
}