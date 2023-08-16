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



class MainActivity : AppCompatActivity() {
    lateinit var EdtEmail: EditText
    lateinit var EdtPassword: EditText
    lateinit var BtLogin: Button
    lateinit var TxtSignup: TextView
    lateinit var mAuth: FirebaseAuth
    lateinit var progress: ProgressDialog
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        EdtEmail = findViewById(R.id.mEdtEmailLogin)
        EdtPassword = findViewById(R.id.medtPass)
        BtLogin = findViewById(R.id.BtnLogin)
        TxtSignup = findViewById(R.id.SignupTxt)
        mAuth = FirebaseAuth.getInstance()
        progress = ProgressDialog(this)
        progress.setTitle("Loading")
        progress.setMessage("Please wait...")
        BtLogin.setOnClickListener {
            //start by receiving data from the user

            var email = EdtEmail.text.toString().trim()
            var password = EdtPassword.text.toString().toString()
            //check if the user is submitting empty fields
            if (email.isEmpty()) {
                EdtEmail.setError("Please fill this input")
                EdtEmail.requestFocus()
            } else if (password.isEmpty()) {
                EdtPassword.setError("Please fill this input")
                EdtPassword.requestFocus()
            //}else if(password.equals(password)){
               // EdtPassword.setError("wrong password")
              //  EdtPassword.requestFocus()
            } else {
                //Proceed to Login the user
                progress.show()
                mAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener {
                    progress.dismiss()
                    if (it.isComplete) {
                        Toast.makeText(this, "Login successful", Toast.LENGTH_SHORT).show()
                        startActivity(Intent(this, HomeActivity::class.java))
                        finish()

                    } else {
                        displayMessage("ERROR", it.exception!!.message.toString())
                    }
                }
            }
        }
        TxtSignup.setOnClickListener {
            startActivity(Intent(applicationContext, RegisterActivity::class.java))
        }
    }

    fun displayMessage(title: String, message: String) {
        var alertDialog = AlertDialog.Builder(this)
        alertDialog.setTitle(title)
        alertDialog.setMessage(message)
        alertDialog.setPositiveButton("Ok", null)
        alertDialog.create().show()
    }

}

