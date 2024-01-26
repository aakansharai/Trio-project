package com.example.instabloodnow.RecyclerModels

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.instabloodnow.R

class AdapterDonneeList(private val donneeList : ArrayList<DoneeDataClass>) : RecyclerView.Adapter<AdapterDonneeList.MyViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.donnee_list, parent, false)
        return MyViewHolder(itemView)
    }

    override fun getItemCount(): Int {
        return donneeList.size
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {

        val currentDonneData = donneeList[position]

        holder.name.text = currentDonneData.name
        holder.location.text = currentDonneData.location
        holder.contact.text = currentDonneData.contact
    }


    class MyViewHolder(itemView : View) : RecyclerView.ViewHolder(itemView){
        val name : TextView = itemView.findViewById(R.id.nameUserDonnee)
        val location : TextView = itemView.findViewById(R.id.donneeLocation)
        val contact : TextView = itemView.findViewById(R.id.contactUserDonnee)
    }
}