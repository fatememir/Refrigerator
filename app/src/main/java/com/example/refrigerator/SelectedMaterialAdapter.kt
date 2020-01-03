
package com.example.refrigerator

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class SelectedMaterialAdapter(
    private val context: Context,
    private val SelectedMaterials: ArrayList<String?>
//    private val onReceived:onMaterialShow
) :
    RecyclerView.Adapter<SelectedMaterialAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            LayoutInflater.from(context).inflate(
                R.layout.item_selected_material,
                parent,
                false
            )
        )
    }

    override fun getItemCount(): Int {
        return SelectedMaterials.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
//                holder.selectIcon.setImageResource(R.drawable.unchecked)
//                holder.details.visibility = View.GONE
        setData(holder, SelectedMaterials[position])

    }

    private fun setData(viewHolder: ViewHolder, SelectedMaterials: String?) {
        viewHolder.title.text = SelectedMaterials
    }

//    interface  onMaterialShow{
//        fun onMaterialShow(response: String)
//    }

    class ViewHolder constructor(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val title: TextView = itemView.findViewById(R.id.materialName)
        val deleteIcon: ImageView = itemView.findViewById(R.id.materialDeleteIcon)

    }
}