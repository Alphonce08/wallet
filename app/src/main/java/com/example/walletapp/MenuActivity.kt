package com.example.walletapp


import androidx.appcompat.app.AppCompatActivity
import android.app.ProgressDialog
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import com.google.firebase.auth.FirebaseAuth



class MenuActivity : AppCompatActivity() {
    lateinit var EdtEmail: EditText
    lateinit var EdtPassword: EditText
    lateinit var BtLogout: Button
    lateinit var mAuth: FirebaseAuth
    lateinit var progress: ProgressDialog
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_menu)
    }
}


