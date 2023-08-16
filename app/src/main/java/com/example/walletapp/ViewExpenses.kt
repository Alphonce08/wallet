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

class ViewExpenses : AppCompatActivity() {

    //initilaise your listview
    lateinit var bnadd:TextView
    lateinit var my_list:ListView
    lateinit var back:ImageView
    //array list
    lateinit var expenses: ArrayList<Expenses>

    //declare adapter
    lateinit var adapter: CustomAdapter


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_view_expenses)
        bnadd = findViewById(R.id.add)
        my_list = findViewById(R.id.mylist)
        back = findViewById(R.id.home)
        bnadd.setOnClickListener {
            startActivity(Intent(applicationContext, ExpensesActivity::class.java))
        }
        back.setOnClickListener {
            startActivity(Intent(this, HomeActivity::class.java))
        }
        expenses = ArrayList()

        adapter = CustomAdapter(this, expenses)

        var ref = FirebaseDatabase.getInstance().getReference().child("expense")

        ref.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                expenses.clear()

                //loop to insert and display
                for (snap in snapshot.children){
                    var userme = snap.getValue(Expenses::class.java)
                    expenses.add(userme!!)
                }
                adapter.notifyDataSetChanged()
            }

            override fun onCancelled(error: DatabaseError) {

                Toast.makeText(this@ViewExpenses, "Database Inaccessible", Toast.LENGTH_SHORT).show()
            }
        })

        my_list.adapter = adapter


    }
}