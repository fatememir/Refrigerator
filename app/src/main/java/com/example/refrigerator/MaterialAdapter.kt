package com.example.refrigerator

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class MaterialAdapter(
    private val context: Context,
    private val materials: ArrayList<MaterialModel>,
    private val onReceived: OnMaterialSelected
) :
    RecyclerView.Adapter<MaterialAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            LayoutInflater.from(context).inflate(
                R.layout.item_material,
                parent,
                false
            )
        )
    }

    override fun getItemCount(): Int {
        return materials.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
//                holder.selectIcon.setImageResource(R.drawable.unchecked)
//                holder.details.visibility = View.GONE
        setData(holder, materials[position])
//        events(holder, position)
    }

    private fun setData(viewHolder: ViewHolder, material: MaterialModel) {
        viewHolder.title.text = material.name
    }

    interface OnMaterialSelected {
        fun onMaterialSelected(response: String)
    }

    class ViewHolder constructor(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val title: TextView = itemView.findViewById(R.id.textView)

    }
}