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
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    private lateinit var materials: ArrayList<MaterialModel>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

       initialize()
        event()
    }

    private fun initialize(){
        materials=ArrayList()
    }
    private fun event(){

        ok_btn.setOnClickListener {

            sendMaterial(editText.toString())
        }
    }

    private fun sendMaterial(
        material: String
    ) {
        val listener = Response.Listener<String> {

            val materialArray = JsonParser().parse(it).asJsonArray
            val materials: ArrayList<MaterialModel> = ArrayList()
            for (i in 0 until materialArray.size()) {
                val material = Gson().fromJson(materialArray[i], MaterialModel::class.java)
                materials.add(material)
            }
            this.materials = materials


            Toast.makeText(this, "connect to server ", Toast.LENGTH_SHORT).show()
//            Log.i("", it.toString())
//            if (it == "OK") {
//                Log.i("TAG3", it.toString())
//                Toast.makeText(this, "connect to server ", Toast.LENGTH_SHORT).show()
//             //javab tooye recycler chap she
//            } else {
//                Toast.makeText(this, "Error", Toast.LENGTH_SHORT).show()
//                Log.i("TAG", it)
//            }
        }
        val error = Response.ErrorListener {
            Toast.makeText(this, "Error2", Toast.LENGTH_SHORT).show()
            Log.i("TAG2", it.toString())
        }
        val request = StringRequest(
            Request.Method.POST,
            "http://192.168.1.105/yakhchal/search_material.php?" +
                    "material=" + material ,
            listener,
            error
        )

        val queue = Volley.newRequestQueue(this)

        queue.add<String>(request)
    }
}
