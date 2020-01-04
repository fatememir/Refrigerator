package com.example.refrigerator

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.KeyEvent
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.google.gson.Gson
import com.google.gson.JsonParser
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity(), MaterialAdapter.OnMaterialSelected {
    private lateinit var materials: ArrayList<MaterialModel>
    private lateinit var selectedMaterials: ArrayList<String?>
    lateinit var listAdapter: MaterialAdapter
    lateinit var selectedAdapter: SelectedMaterialAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initialize()
        event()
    }

    private fun initialize() {
        materials = ArrayList()
        selectedMaterials = ArrayList()
//        val bundle: Bundle? = intent.extras
//        var name: String? = bundle?.getString("materialName")
//        selectedMaterials.add(name)


    }

    private fun event() {


        editText.setOnKeyListener { v, keyCode, event ->
            //You can identify which key pressed buy checking keyCode value with KeyEvent.KEYCODE_
            if (keyCode == KeyEvent.KEYCODE_DEL) {
                for (i in 0 until materials.size) {
                    materials.clear()
                }
                listAdapter.notifyDataSetChanged()
            }
            false
        }
        editText.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {

            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {

            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                if (editText.text.toString().isNotEmpty()) {
                    sendMaterial(editText.text.toString())
                } else {
                    try {
                        materials.clear()
                        //                        materials.removeAt(i)
                    } catch (e: Exception) {

                    }

                    listAdapter.notifyDataSetChanged()
                }
            }

        })
        ok_btn.setOnClickListener {
            sendMaterial(editText.text.toString())

        }
    }

    private fun showSelectedMaterials() {

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

//                Toast.makeText(this,  materials.toString(), Toast.LENGTH_SHORT).show()
                listAdapter = MaterialAdapter(this, materials, this)
                recyclerView.adapter = listAdapter
                recyclerView.layoutManager =
                    LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
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
            "Link -> http://192.168.1.100/yakhchal/search_material.php?material=$material"
        )

        val request = StringRequest(
            Request.Method.POST,
            "http://192.168.1.100/yakhchal/search_material.php?" +
                    "material=" + material,
            listener,
            error
        )

        val queue = Volley.newRequestQueue(this)

        queue.add<String>(request)
    }


    override fun onMaterialSelected(response: String) {
        Log.i("material", "onAddressSelected $response")
        selectedMaterials.add(response)
        recyclerView.adapter = MaterialAdapter(this, materials, this)
        if (selectedMaterials.isNotEmpty()) {
            selectedMaterialRecycler.adapter = SelectedMaterialAdapter(this, selectedMaterials)
            selectedMaterialRecycler.layoutManager =
                LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)

            var isThere = false
            for (i in 0 until selectedMaterials.size) {
                if (response == selectedMaterials[i]) {
                    isThere = true
                }
            }
            if (!isThere) {
                selectedMaterials.add(response)
                listAdapter.notifyItemInserted(materials.size - 1)
                listAdapter.notifyDataSetChanged()
            }

        }
        Toast.makeText(this, response.toString(), Toast.LENGTH_SHORT).show()

//        apiService.getVehiclePrices(
//            this,
//            userData.getId(),
//            userData.getToken(),
//            selectedAddress.lat.toDouble(),
//            selectedAddress.lang.toDouble()
//        )
    }

//   override fun onMaterialShow(response: String) {
//        Log.i("material","onAddressSelected $response")
//        selectedMaterialRecycler.adapter = SelectedMaterialAdapter(this, SelectedMaterials,this)
//
////        apiService.getVehiclePrices(
////            this,
////            userData.getId(),
////            userData.getToken(),
////            selectedAddress.lat.toDouble(),
////            selectedAddress.lang.toDouble()
////        )
//    }
}
