package com.example.walletapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import android.widget.ListView
import android.widget.TextView
import android.widget.Toast
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener

class ViewIncome : AppCompatActivity() {

    //initilaise your listview
    lateinit var nbadd:TextView
    lateinit var My_list: ListView
    lateinit var back:ImageView
    //array list
    lateinit var income: ArrayList<Expenses>

    //declare adapter
    lateinit var adapter: CustomAdapter


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_view_income)

        My_list = findViewById(R.id.mylist)
        nbadd = findViewById(R.id.madd)
        back = findViewById(R.id.home)
        back.setOnClickListener {
            startActivity(Intent(applicationContext, HomeActivity::class.java))
        }
        nbadd.setOnClickListener {
            startActivity(Intent(this, IncomeActivity::class.java))
        }
        income = ArrayList()

        adapter = CustomAdapter(this, income)

        var ref = FirebaseDatabase.getInstance().getReference().child("income")

        ref.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                income.clear()

                //loop to insert and display
                for (snap in snapshot.children){
                    var userme = snap.getValue(Expenses::class.java)
                    income.add(userme!!)
                }
                adapter.notifyDataSetChanged()
            }

            override fun onCancelled(error: DatabaseError) {

                Toast.makeText(this@ViewIncome, "Database Inaccessible", Toast.LENGTH_SHORT).show()
            }
        })

        My_list.adapter = adapter


    }
}