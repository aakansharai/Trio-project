package com.example.instabloodnow.fragments

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.cardview.widget.CardView
import com.example.instabloodnow.R
import com.example.instabloodnow.SignIn

class Notification : Fragment() {

    lateinit var builder : AlertDialog.Builder

    @SuppressLint("MissingInflatedId")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_notification, container, false)

        val accept : Button = view.findViewById(R.id.acceptDonationRequest)
        val deny : Button = view.findViewById(R.id.denyDonationRequest)

        val accept2 : Button = view.findViewById(R.id.acceptDonationRequest2)
        val deny2 : Button = view.findViewById(R.id.denyDonationRequest2)


        val card : CardView = view.findViewById(R.id.card1)
        val card2 : CardView = view.findViewById(R.id.card2)


        accept.setOnClickListener {
            builder = context?.let { it1 -> AlertDialog.Builder(it1) }!!
            builder
                .setTitle("Accepting Request!")
                .setMessage("This declares that, You are ready to donate blood to the respective recipient.")
                .setNegativeButton("Not now"){ _ ,_ ->
                    Toast.makeText(context, "Not accepted", Toast.LENGTH_SHORT).show()
                }
                .setPositiveButton("Accept"){ _, _ ->
                    Toast.makeText(context, "Accepted", Toast.LENGTH_SHORT).show()
                    card.visibility = View.GONE
                }.show()
        }
        accept2.setOnClickListener {
            builder = context?.let { it1 -> AlertDialog.Builder(it1) }!!
            builder
                .setTitle("Accepting Request!")
                .setMessage("This declares that, You are ready to donate blood to the respective recipient.")
                .setNegativeButton("Not now"){ _ ,_ ->
                    Toast.makeText(context, "Not accepted", Toast.LENGTH_SHORT).show()
                }
                .setPositiveButton("Accept"){ _, _ ->
                    Toast.makeText(context, "Accepted", Toast.LENGTH_SHORT).show()
                    card2.visibility = View.GONE
                }.show()
        }
        deny.setOnClickListener {
            builder = context?.let { it1 -> AlertDialog.Builder(it1) }!!
            builder
                .setTitle("Rejecting Request!")
                .setMessage("This declares that, You are NOT ready to donate blood to the respective recipient.")
                .setNegativeButton("Not now"){ _ ,_ ->
                    Toast.makeText(context, "Not accepted", Toast.LENGTH_SHORT).show()
                }
                .setPositiveButton("Reject"){ _, _ ->
                    Toast.makeText(context, "Rejected", Toast.LENGTH_SHORT).show()
                    card.visibility = View.GONE
                }.show()
        }
        deny2.setOnClickListener {
            builder = context?.let { it1 -> AlertDialog.Builder(it1) }!!
            builder
                .setTitle("Rejecting Request!")
                .setMessage("This declares that, You are NOT ready to donate blood to the respective recipient.")
                .setNegativeButton("Not now"){ _ ,_ ->
                    Toast.makeText(context, "Not accepted", Toast.LENGTH_SHORT).show()
                }
                .setPositiveButton("Reject"){ _, _ ->
                    Toast.makeText(context, "Rejected", Toast.LENGTH_SHORT).show()
                    card2.visibility = View.GONE
                }.show()
        }

        return view
    }

}