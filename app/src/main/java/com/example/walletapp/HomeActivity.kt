package com.example.walletapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView

class HomeActivity : AppCompatActivity() {
    lateinit var bmHome:ImageView
    lateinit var ImgExpenses:ImageView
    lateinit var btIncome:ImageView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)
        bmHome = findViewById(R.id.home)
        ImgExpenses = findViewById(R.id.mBimag)
        btIncome = findViewById(R.id.income)
        bmHome.setOnClickListener {
            startActivity(Intent(applicationContext, MenuActivity::class.java))
        }
        ImgExpenses.setOnClickListener {
            startActivity(Intent(this, ViewExpenses::class.java))
        }
        btIncome.setOnClickListener {
            startActivity(Intent(this, ViewIncome::class.java))
        }
    }
}
