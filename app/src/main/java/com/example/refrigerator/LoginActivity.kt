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
import com.google.gson.Gson
import com.google.gson.JsonParser
import kotlinx.android.synthetic.main.activity_login.*

class LoginActivity : AppCompatActivity() {
    private lateinit var users: ArrayList<UserModel>
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        users = ArrayList()

//        checkUserLogin()
        loginSignUpButton.setOnClickListener {
            val intent = Intent(this, SignUpActivity::class.java)
            startActivity(intent)
        }

        okButton.setOnClickListener {
            selectData()
        }

    }

    private fun selectData() {
        val listener = Response.Listener<String> {
            Log.i("selectData", it)
            val usersArray = JsonParser().parse(it).asJsonArray
            val users: ArrayList<UserModel> = ArrayList()
            for (i in 0 until usersArray.size()) {
                val user = Gson().fromJson(usersArray[i], UserModel::class.java)
                users.add(user)
            }
            this.users = users
            Log.i("users", users.toString())
            checkUserLogin()

//            if(userNameLogin.text==users){
//            this.students = students
//            resultRecycler.adapter = CustomAdapter(this, students)
//            resultRecycler.layoutManager = LinearLayoutManager(this)
        }
        val errorListener = Response.ErrorListener {
            Toast.makeText(this, it.toString(), Toast.LENGTH_SHORT).show()
            Log.i("selectData", it.toString())
        }
        val request = StringRequest(
            Request.Method.GET,
            "http://192.168.1.100/New%20folder/select.php",
            listener,
            errorListener
        )

        val queue = Volley.newRequestQueue(this)

        queue.add<String>(request)
    }

    private fun checkUserLogin() {
        var loggedIn = false
        for (i in 0 until users.size) {
            if (users[i].username == userNameLogin.text.toString()) {
                if (users[i].password == passLogin.text.toString()) {

                    val intent = Intent(this, MainActivity::class.java)
                    startActivity(intent)

                    loggedIn = true
                }
            }
        }
        if (!loggedIn) {
            Toast.makeText(this, "user or password don't not exist", Toast.LENGTH_SHORT).show()
        }
    }
}
