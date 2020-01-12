package com.example.refrigerator.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.refrigerator.R

class FilterAdapter(
    private val context: Context,
    private val Filters: ArrayList<String?>
) :
    RecyclerView.Adapter<FilterAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            LayoutInflater.from(context).inflate(
                R.layout.item_main_filter,
                parent,
                false
            )
        )
    }

    override fun getItemCount(): Int {
        return Filters.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        setData(holder, Filters[position])
    }

    private fun setData(viewHolder: ViewHolder, SelectedMaterials: String?) {
        viewHolder.title.text = SelectedMaterials
    }

    class ViewHolder constructor(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val title: TextView = itemView.findViewById(R.id.FilterName)
    }
}