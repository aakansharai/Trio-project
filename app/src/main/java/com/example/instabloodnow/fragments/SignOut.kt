package com.example.instabloodnow.fragments

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import com.example.instabloodnow.MainActivity
import com.example.instabloodnow.R
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener

class SignOut : Fragment() {

    lateinit var db : DatabaseReference

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_sign_out, container, false)

        db = FirebaseDatabase.getInstance().getReferenceFromUrl("https://instabloodnow-default-rtdb.firebaseio.com/")

//        val bundle = arguments
//        val message = bundle!!.getString("id")
//        Log.w("AAA-AAA", message.toString())

        val sigOut : Button = view.findViewById(R.id.signOutBtn)
        sigOut.setOnClickListener {
            val intent = Intent(context, MainActivity::class.java)
            startActivity(intent)
        }
//        val userIdMain = message.toString()
//        Toast.makeText(context, userIdMain, Toast.LENGTH_SHORT).show()

//        val name : TextView = view.findViewById(R.id.greeting)
//        db.child("Users").addListenerForSingleValueEvent(object: ValueEventListener {
//            override fun onDataChange(snapshot: DataSnapshot) {
//                if(snapshot.hasChild(userIdMain)){
//
//                    val Name = snapshot.child(userIdMain).child("name").getValue(String::class.java)
//                    name.text = Name.toString()
//
//                }
//                else{
//                    Toast.makeText(context, userIdMain, Toast.LENGTH_SHORT).show()
//                }
//            }
//
//            override fun onCancelled(error: DatabaseError) {
//                Toast.makeText(context, "Something went wrong", Toast.LENGTH_SHORT).show()
//            }
//
//        })
        return view
    }

}