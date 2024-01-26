package com.example.instabloodnow

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.ktx.values

class SignIn : AppCompatActivity() {

    lateinit var databaseF : DatabaseReference
    lateinit var auth : FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_in)

        auth = FirebaseAuth.getInstance()

        val signIn : Button = findViewById(R.id.SignInButton)
        val email : EditText = findViewById(R.id.phoneSignIn)
        val pass : EditText = findViewById(R.id.aadharSignIn)

        databaseF = FirebaseDatabase.getInstance().getReferenceFromUrl("https://instabloodnow-default-rtdb.firebaseio.com/")

        signIn.setOnClickListener {

//====================+ CANDIDATE VERIFICATION +===============================

            val userId = email.text.toString()
            val password = pass.text.toString()

            databaseF.child("Users").addListenerForSingleValueEvent(object: ValueEventListener{
                override fun onDataChange(snapshot: DataSnapshot) {
                    if(snapshot.hasChild(userId)) {
                        val getPassword = snapshot.child(userId).child("password").getValue(String::class.java)
                        if(password == getPassword) {
                            Toast.makeText(this@SignIn, "Candidate verified Successfully!!", Toast.LENGTH_SHORT).show()

//================================ sending userId to MAIN HOME PAGE +==========================
                            val bundle = Bundle()
                            bundle.putString("id", userId)

                            val intent = Intent(this@SignIn, UserHome::class.java)
                            intent.putExtras(bundle)
                            startActivity(intent)

                        } else {
                            Toast.makeText(this@SignIn, "Invalid userId or password!", Toast.LENGTH_SHORT).show()
                        }
                    } else{
                        Toast.makeText(this@SignIn, "Data not exists!", Toast.LENGTH_SHORT).show()
                    }
                }

                override fun onCancelled(error: DatabaseError) {
                    Toast.makeText(this@SignIn, "SignIn Cancelled!!", Toast.LENGTH_SHORT).show()
                }

            })
        }
    }
}