package com.example.instabloodnow.fragments

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.example.instabloodnow.R
import com.example.instabloodnow.UserDetails.DonatioRecordsData
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase


class DonationRecords : Fragment() {


    @SuppressLint("MissingInflatedId")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view=  inflater.inflate(R.layout.fragment_donation_records, container, false)




        return view
    }


}