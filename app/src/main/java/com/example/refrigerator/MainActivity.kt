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

    private fun initialize() {
        materials = ArrayList()
    }

    private fun event() {
        editText.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {

            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {

            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                sendMaterial(editText.text.toString())
            }

        })
        ok_btn.setOnClickListener {
            sendMaterial(editText.text.toString())
        }
    }

    private fun sendMaterial(material: String) {
        val listener = Response.Listener<String> { response ->
            try {
                Log.i("CurrentTag", "ConnectToServer $response")

                val materialArray = JsonParser().parse(response).asJsonArray
                val materials: ArrayList<MaterialModel> = ArrayList()
                for (i in 0 until materialArray.size()) {
                    val material = Gson().fromJson(materialArray[i], MaterialModel::class.java)
                    materials.add(material)
                }
                this.materials = materials
            } catch (e: Exception) {
                Log.i("CurrentTag", "ConnectToServer $e")
            }
        }
        val error = Response.ErrorListener {
            Toast.makeText(this, "Error2", Toast.LENGTH_SHORT).show()
            Log.i("CurrentTag", it.toString())
        }
        Log.i(
            "CurrentTag",
            "Link -> http://192.168.1.58/yakhchal/search_material.php?material=$material"
        )

        val request = StringRequest(
            Request.Method.POST,
            "http://192.168.1.58/yakhchal/search_material.php?" +
                    "material=" + material,
            listener,
            error
        )

        val queue = Volley.newRequestQueue(this)

        queue.add<String>(request)
    }
}
