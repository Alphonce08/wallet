
package com.example.walletapp

import android.app.ProgressDialog
import android.content.Intent
import android.drm.ProcessedData
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import com.google.firebase.auth.FirebaseAuth

class RegisterActivity : AppCompatActivity() {
    lateinit var edtemail: TextView
    lateinit var edtpasswrd: TextView
    lateinit var edtconfirmpass:TextView
    lateinit var BtnRegister: Button
    lateinit var progress: ProgressDialog
    lateinit var mAuth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)
        BtnRegister = findViewById(R.id.BtnRegister)
        edtemail = findViewById(R.id.mEdtEmail)
        edtpasswrd = findViewById(R.id.mEdtPassword)
        edtconfirmpass = findViewById(R.id.edtConfirmPass)
        mAuth = FirebaseAuth.getInstance()
        progress = ProgressDialog(this)
        progress.setTitle("Loading")
        progress.setMessage("Please wait...")
        BtnRegister.setOnClickListener {
            //start by receiving data from the user
            var email = edtemail.text.toString().trim()
            var password = edtpasswrd.text.toString().toString()
            var confirmpassword = edtconfirmpass.text.toString().toString()
            //check if the user is submitting empty fields
            if (email.isEmpty()){
                edtemail.setError("Please fill this input")
                edtemail.requestFocus()
            } else if (password.isEmpty()){
                edtpasswrd.setError("Please fill this input")
                edtpasswrd.requestFocus()
            }else if(password.length < 6 ) {
                edtpasswrd.setError("Password is too short")
                edtpasswrd.requestFocus()
            }else if(password.length.equals(edtconfirmpass) ) {
                edtpasswrd.setError("matched your password")
                edtpasswrd.requestFocus()
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

    }




fun displayMessage(title: String, message: String) {
    var alertDialog = AlertDialog.Builder(this)
    alertDialog.setTitle(title)
    alertDialog.setMessage(message)
    alertDialog.setPositiveButton("Ok", null)
    alertDialog.create().show()
}

}
