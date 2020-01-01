package com.example.refrigerator

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import kotlinx.android.synthetic.main.activity_sign_up.*

class SignUpActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_up)

        saveButton.setOnClickListener {
            checkEdittext()
        }
    }

    private fun insertData(
        firstname: String,
        lastname: String,
        password: String,
        phonenumber: String,
        username: String
    ) {
        val listener = Response.Listener<String> {
            Log.i("", it.toString())
            if (it == "OK") {
                Log.i("TAG3", it.toString())
                Toast.makeText(this, "save successfully!", Toast.LENGTH_SHORT).show()
                val intent = Intent(this, LoginActivity::class.java)
                startActivity(intent)
            } else {
                Toast.makeText(this, "Error", Toast.LENGTH_SHORT).show()
                Log.i("TAG", it)
            }
        }
        val error = Response.ErrorListener {
            Toast.makeText(this, "Error2", Toast.LENGTH_SHORT).show()
            Log.i("TAG2", it.toString())
        }
        val request = StringRequest(
            Request.Method.POST,
            "http://192.168.1.100/New%20folder/signUp.php?" +
                    "firstname=" + firstname +
                    "&lastname=" + lastname +
                    "&password=" + password +
                    "&phonenumber=" + phonenumber +
                    "&username=" + username,
            listener,
            error
        )

        val queue = Volley.newRequestQueue(this)

        queue.add<String>(request)
    }

    private fun checkEdittext() {
        if (nameEditText.text.toString().isEmpty()) {
            Toast.makeText(this, "لطفا نام خود را وارد کنید", Toast.LENGTH_SHORT).show()
            nameEditText.requestFocus()
        } else if (lastNameEdittext.text.toString().isEmpty()) {
            Toast.makeText(this, "لطفا نام خانوادگی خود را وارد کنید", Toast.LENGTH_SHORT).show()
            lastNameEdittext.requestFocus()
        } else if (userNameEdittext.text.toString().isEmpty()) {
            Toast.makeText(this, "لطفا نام کاربری خود را وارد کنید", Toast.LENGTH_SHORT).show()
            userNameEdittext.requestFocus()
        } else if (passwordEidtex.text.toString().isEmpty()) {
            Toast.makeText(this, "لطفا رمز خود را وارد کنید", Toast.LENGTH_SHORT).show()
            passwordEidtex.requestFocus()
        }
        else if (phonNumberEdittext.text.toString().isEmpty()) {
            Toast.makeText(this, "لطفا شماره ی خود را وارد کنید", Toast.LENGTH_SHORT).show()
            phonNumberEdittext.requestFocus()
        } else if (!phonNumberEdittext.text.toString().startsWith("09")) {
            Toast.makeText(this, "شماره ی وارد شده صحیح نمی باشد", Toast.LENGTH_SHORT).show()
            phonNumberEdittext.requestFocus()
        } else if (phonNumberEdittext.text.toString().length != 11) {
            Toast.makeText(this, "لطفا 11 رقم وارد کنید", Toast.LENGTH_SHORT).show()
            phonNumberEdittext.requestFocus()
        } else {
            insertData(
                nameEditText.text.toString(),
                lastNameEdittext.text.toString(),
                passwordEidtex.text.toString(),
                phonNumberEdittext.text.toString(),
                userNameEdittext.text.toString()
            )
        }
    }}


