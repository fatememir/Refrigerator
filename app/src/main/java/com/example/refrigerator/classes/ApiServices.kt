package com.example.refrigerator.classes

import android.content.Context
import android.util.Log
import com.android.volley.Response
import com.example.refrigerator.MaterialModel
import com.google.gson.Gson
import com.google.gson.JsonParser

class ApiServices constructor(private val context: Context){

    val listener = Response.Listener<String> { response ->
//        try {
//            Log.i("CurrentTag", "ConnectToServer $response")
//            val materialArray = JsonParser().parse(response).asJsonArray
//            val materials: ArrayList<MaterialModel> = ArrayList()
//            for (i in 0 until materialArray.size()) {
//                val material = Gson().fromJson(materialArray[i], MaterialModel::class.java)
//                materials.add(material)
//            }
//        }
    }
        interface OnRecived {
            fun onRecived(response: String)
        }
    }