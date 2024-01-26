package com.example.instabloodnow.fragments

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import android.widget.TextView
import android.widget.Toast
import com.example.instabloodnow.R
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.ktx.getValue

class Profile : Fragment() {

    lateinit var db : DatabaseReference
    @SuppressLint("MissingInflatedId")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view =  inflater.inflate(R.layout.fragment_profile, container, false)

        val name : TextView = view.findViewById(R.id.nameProfileFragmentValue)
        val email : TextView = view.findViewById(R.id.EmailIdProfileFragmentValue)
        val location : TextView = view.findViewById(R.id.LocationPreferenceProfileFragmentValue)
        val userId : TextView = view.findViewById(R.id.UserIdProfileFragmentValue)
        val bloodGroup : TextView = view.findViewById(R.id.BloodGroupProfileFragmentValue)
        val titleName : TextView = view.findViewById(R.id.greeting)
        val progress : ProgressBar = view.findViewById(R.id.profileProgressBar)


        db = FirebaseDatabase.getInstance().getReferenceFromUrl("https://instabloodnow-default-rtdb.firebaseio.com/")

        val bundle = arguments
        val message = bundle!!.getString("id")
        Log.w("AAA-AAA", message.toString())

        val userIdMain = message.toString()

        db.child("Users").addListenerForSingleValueEvent(object: ValueEventListener{
            override fun onDataChange(snapshot: DataSnapshot) {
                if(snapshot.hasChild(userIdMain)){
                    progress.visibility = View.GONE

                    val Name = snapshot.child(userIdMain).child("name").getValue(String::class.java)
                    name.text = Name.toString()
                    name.visibility = View.VISIBLE
                    userId.text = snapshot.child(userIdMain).child("userId").getValue(Int.toString()::class.java)
                    userId.visibility = View.VISIBLE

                    location.text = snapshot.child(userIdMain).child("location").getValue(String::class.java)
                    location.visibility = View.VISIBLE

                    email.text = snapshot.child(userIdMain).child("email").getValue(String::class.java)
                    email.visibility = View.VISIBLE

                    bloodGroup.text = snapshot.child(userIdMain).child("bloodGrp").getValue(String::class.java)
                    bloodGroup.visibility = View.VISIBLE


                    titleName.text = "Hello!!  ${Name.toString()}"
                    titleName.visibility = View.VISIBLE

                }
                else{
                    Toast.makeText(context, "Data is NOT here", Toast.LENGTH_SHORT).show()
                }
            }

            override fun onCancelled(error: DatabaseError) {
                Toast.makeText(context, "Something went wrong", Toast.LENGTH_SHORT).show()
            }

        })

//        db = FirebaseDatabase.getInstance().getReference("Users")
//        db.child("524").get().addOnSuccessListener {
//
//            val fullName = it.child("name").value
//            val emailId = it.child("email").value
//            val locationUser = it.child("location").value
//            val UserId = it.child("userId").value
//
//            name.text = fullName.toString()
//            email.text = emailId.toString()
//            location.text = locationUser.toString()
//            userId.text = UserId.toString()
//        }

        return view
    }

}