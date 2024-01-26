package com.example.instabloodnow

import android.Manifest
import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.telephony.SmsManager
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.ProgressBar
import android.widget.Spinner
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.core.app.ActivityCompat
import com.example.instabloodnow.UserDetails.UserData
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import java.lang.Exception
import java.security.Permission

class Registration : AppCompatActivity() {

    lateinit var auth : FirebaseAuth
    var db : DatabaseReference = FirebaseDatabase.getInstance().getReferenceFromUrl("https://instabloodnow-default-rtdb.firebaseio.com/")
    private val REQUEST_PERMISSIONS_REQUEST_CODE = 34

    lateinit var builder : AlertDialog.Builder

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_registration)

        auth = FirebaseAuth.getInstance()
//        db = FirebaseDatabase.getInstance().getReferenceFromUrl("https://instabloodnow-default-rtdb.firebaseio.com/")

        val register : Button = findViewById(R.id.resisterBtn)
        val email : EditText = findViewById(R.id.email)
        val pass : EditText = findViewById(R.id.password)
        val contact : EditText = findViewById(R.id.Contact)
        val name : EditText = findViewById(R.id.fullName)
        val location : Spinner = findViewById(R.id.Location)
        val bg : Spinner = findViewById(R.id.BloodGroup)


        register.setOnClickListener {
            val emailAdd = email.text.toString()
            val password = pass.text.toString()
            val contactNo = contact.text.toString()
            val nameUser = name.text.toString()
            val loca = location.selectedItem.toString()
            val BG = bg.selectedItem.toString()
            val progress : ProgressBar = findViewById(R.id.progressBar)

            if(emailAdd.isNotEmpty() && password.isNotEmpty() && contactNo.isNotEmpty()){

                progress.visibility = View.VISIBLE

                val userId = userRandomNumber().toString()
                val id = userId
                val userData = UserData(userId, nameUser, contactNo, emailAdd, password, loca, BG, false)

                // Checking if ID is already EXISTS
                db.child("Users").addListenerForSingleValueEvent(object : ValueEventListener{
                    override fun onDataChange(snapshot: DataSnapshot) {
                        if(snapshot.hasChild(id)){
                            Toast.makeText(this@Registration, "Try Again!!", Toast.LENGTH_SHORT).show()
                        }
                        else{
                            //=========== Creating New User

                            db.child(id).setValue(userData).addOnCompleteListener {
                                if(it.isSuccessful) {

                                    //========== ALERT BOX
                                    builder = AlertDialog.Builder(this@Registration)
                                    progress.visibility = View.GONE
                                    builder
                                        .setTitle("User Id : $id")
                                        .setMessage("Your userId, \nTake SCREEN SHOT of this to remember your Id. \nUse this at SIGN IN")
                                        .setNegativeButton("I will remember"){ _ ,_ ->
                                            Toast.makeText(this@Registration, "$id", Toast.LENGTH_SHORT).show()
                                            val intent = Intent(this@Registration, SignIn::class.java)
                                            startActivity(intent)
                                        }
                                        .setPositiveButton("Done"){ _, _ ->
                                            Toast.makeText(this@Registration, id, Toast.LENGTH_SHORT).show()
                                            val intent = Intent(this@Registration, SignIn::class.java)
                                            startActivity(intent)
                                        }.show()

                                    db.child("Rewards").child(userId).child("Rewards").setValue("")

                                }
                                else{
                                    Toast.makeText(this@Registration, "User Not created! process not successful", Toast.LENGTH_SHORT).show()
                                }
                            }
                        }
                    }

                    override fun onCancelled(error: DatabaseError) {
                        Toast.makeText(this@Registration, "User Not created!!", Toast.LENGTH_SHORT).show()
                    }
                })
            }
            else{
                Toast.makeText(this@Registration, "All fields Must Filled!", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun userRandomNumber(): Int {
        var num = (100..999).shuffled().last()
        val number = num.toString()
        if(checkIdExists(number)){
            Toast.makeText(this, "Something went wrong! Try Again", Toast.LENGTH_SHORT).show()
        }
        else{
            return num
        }
        return num
    }

    private fun checkIdExists(number: String): Boolean {
        db = FirebaseDatabase.getInstance().getReference("Users")
        var const = false
        db.child("Users").addListenerForSingleValueEvent(object : ValueEventListener{
            override fun onDataChange(snapshot: DataSnapshot) {
                const = snapshot.hasChild(number)
            }
            override fun onCancelled(error: DatabaseError) {
                const = false
            }

        })
        return const
    }
}