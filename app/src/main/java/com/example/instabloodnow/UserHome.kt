package com.example.instabloodnow

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.widget.SwitchCompat
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import com.example.instabloodnow.fragments.DonationRecords
import com.example.instabloodnow.fragments.DonationRewards
import com.example.instabloodnow.fragments.HomeSweetHome
import com.example.instabloodnow.fragments.Notification
import com.example.instabloodnow.fragments.Profile
import com.example.instabloodnow.fragments.SignOut
import com.google.android.material.navigation.NavigationView
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class UserHome : AppCompatActivity() {

    lateinit var toggle : ActionBarDrawerToggle
    lateinit var drawerLayout : DrawerLayout
    lateinit var db : DatabaseReference
    lateinit var titleName : TextView
    lateinit var navBtn : ImageView

    lateinit var builder : AlertDialog.Builder

    @SuppressLint("RtlHardcoded")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_user_home)

//====================   ALL ABOUT NAVIGATION   =========================

        drawerLayout = findViewById(R.id.Drawer)
        val navigation : NavigationView = findViewById(R.id.navigation)
        navBtn = findViewById(R.id.sideNavOpen)

        navBtn.setOnClickListener {
            if(drawerLayout.isDrawerOpen(GravityCompat.START)){
                navBtn.setImageResource(R.drawable.hamburger)
                drawerLayout.closeDrawer(GravityCompat.START)

            }else{
                drawerLayout.openDrawer(GravityCompat.START)
                navBtn.setImageResource(R.drawable.close)
            }
        }

        val fragment = HomeSweetHome()
        val transaction : FragmentTransaction = supportFragmentManager.beginTransaction()
        transaction.replace(R.id.frameLayout, fragment)
        transaction.commit()

        val bundle = intent.extras
        var tvId: String ?= null
        if (bundle != null) {
            tvId = "${bundle.getString("id")}"
        }

        db = FirebaseDatabase.getInstance().getReferenceFromUrl("https://instabloodnow-default-rtdb.firebaseio.com/")
        val st = db.child("Users").child(tvId.toString()).child("status").key.toString()

        navigation.setNavigationItemSelectedListener {

            it.isChecked = true     // highlighting the item which is selected

            when (it.itemId) {
                R.id.profile ->{
                    val fragment = Profile()
                    val mBundle = Bundle()
                    mBundle.putString("id", tvId.toString())
                    fragment.arguments = mBundle
                    replaceFragment(fragment, it.title.toString())
                }

                R.id.rewards -> {
                    val fragment = DonationRewards()
                    val m1bundle = Bundle()
                    m1bundle.putString("id", tvId.toString())
                    fragment.arguments = m1bundle
                    replaceFragment(fragment, it.title.toString())
                }
                R.id.RecordsOfDonation -> replaceFragment(DonationRecords(), it.title.toString())
                R.id.SignOut -> replaceFragment(SignOut(), it.title.toString())
                R.id.HomeMainHome -> replaceFragment(HomeSweetHome(), it.title.toString())
                R.id.Notifications -> replaceFragment(Notification(), it.title.toString())


            }
            true
        }


//====================   SWITCH TOGGLE   =========================

        val switch : SwitchCompat = findViewById(R.id.switchBtn)
        builder = AlertDialog.Builder(this)

        switch.setOnClickListener {
            if(switch.isChecked){
                builder
                    .setTitle("Available for Blood Donation.")
                    .setMessage("Will you be available to donate blood at your preferred location ?")
                    .setNegativeButton("No") { _, _ ->
                        switch.isChecked = false
                        switch.text = "Not Available to Donate"
                        db.child("Users").child(tvId.toString()).child("status").setValue(false)

                    }
                    .setPositiveButton("Yes"){ _, _ ->
                        switch.isChecked = true
                        Toast.makeText(this, "You are Available to donate blood.", Toast.LENGTH_SHORT).show()
                        db.child("Users").child(tvId.toString()).child("status").setValue(true)
                        switch.text = "Available to Donate"
                    }.show()
            }
            else{
                builder
                    .setTitle("Not Available for Blood Donation")
                    .setMessage("You are Not Available for Blood Donation.")
                    .setNegativeButton("No") { p0, p1 ->
                        switch.isChecked = true
                        switch.text = "Available to Donate"
                        db.child("Users").child(tvId.toString()).child("status").setValue(true)

                    }
                    .setPositiveButton("Yes"){ p0, p1 ->
                        switch.isChecked = false
                        switch.text = "Not Available to Donate"
                        Toast.makeText(this, "You are not Available to donate blood.", Toast.LENGTH_SHORT).show()
                        db.child("Users").child(tvId.toString()).child("status").setValue(false)
                    }.show()
            }
        }
    }

    private fun replaceFragment(fragment: Fragment, title: String){
        val fragmentManager = supportFragmentManager
        val fragmentTransection = fragmentManager.beginTransaction()
        fragmentTransection.replace(R.id.frameLayout, fragment)
        fragmentTransection.commit()

        drawerLayout.closeDrawers()

        titleName = findViewById(R.id.titleHome)
        navBtn = findViewById(R.id.sideNavOpen)
        titleName.text = title
        navBtn.setImageResource(R.drawable.hamburger)

    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if(toggle.onOptionsItemSelected(item)){
            return true
        }
        return super.onOptionsItemSelected(item)
    }

}
