package com.example.instabloodnow.fragments

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.ProgressBar
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.instabloodnow.R
import com.example.instabloodnow.RecyclerModels.AdapterDonneeList
import com.example.instabloodnow.RecyclerModels.DoneeDataClass
import com.example.instabloodnow.SignIn
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener


class HomeSweetHome : Fragment() {

    lateinit var db : DatabaseReference
    lateinit var donneeArrayList : ArrayList<DoneeDataClass>
    lateinit var recycler : RecyclerView
    lateinit var progress : ProgressBar
    lateinit var builder : AlertDialog.Builder

    @SuppressLint("MissingInflatedId")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view =  inflater.inflate(R.layout.fragment_home_sweet_home, container, false)
        val view2 =  inflater.inflate(R.layout.donnee_list, container, false)


        recycler = view.findViewById(R.id.HomeRecycleContainer)
        progress = view.findViewById(R.id.donneeListProgressBar)
        progress.visibility = View.VISIBLE
        val searchLocation : EditText = view.findViewById(R.id.searchDonnee)

        val location = "North delhi"
        donneeArrayList = arrayListOf<DoneeDataClass>()

        db = FirebaseDatabase.getInstance().getReferenceFromUrl("https://instabloodnow-default-rtdb.firebaseio.com/")
        db.child("Users").addListenerForSingleValueEvent(object: ValueEventListener{
            override fun onDataChange(snapshot: DataSnapshot) {
                if(snapshot.exists()) {
                    for(userSnapshot in snapshot.children){
                        val l1 = userSnapshot.child("userId").child(location).key.toString()
                        if (l1 == location) {
                            Log.w("AAA-AA", l1)
                            val user = userSnapshot.getValue(DoneeDataClass::class.java)
                            donneeArrayList.add(user!!)
                        }
                    }
                    progress.visibility = View.GONE
                    recycler.adapter = AdapterDonneeList(donneeArrayList)
                    val llm = LinearLayoutManager(context)
                    llm.orientation = LinearLayoutManager.VERTICAL
                    recycler.layoutManager = llm
                }
            }

            override fun onCancelled(error: DatabaseError) {
                TODO("Not yet implemented")
            }

        })

        val sendRequest : Button = view2.findViewById(R.id.sendDonneeRequest)
        sendRequest.setOnClickListener {
            Toast.makeText(context, "Hellooooooo", Toast.LENGTH_SHORT).show()

//========== ALERT BOX
//            builder = AlertDialog.Builder(requireContext())
//            builder
//                .setTitle("User Id : $id")
//                .setMessage("Your userId, \nTake SCREEN SHOT of this to remember your Id. \nUse this at SIGN IN")
//                .setNegativeButton("I will remember"){ _ ,_ ->
//
//                }
//                .setPositiveButton("Took SS"){ _, _ ->
//                    Toast.makeText(context, id, Toast.LENGTH_SHORT).show()
//
//                }.show()

        }

        return view
    }
}