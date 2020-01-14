package com.example.refrigerator.views

import android.content.Context
import android.graphics.Canvas
import android.graphics.Paint
import android.util.AttributeSet
import android.view.View
import android.widget.TextView
import com.example.refrigerator.R


class ButtonShape(context: Context, attrs: AttributeSet) : View(context, attrs) {
//    lateinit var  text: TextView
    private var background = R.color.blue
    private var textColor = "#fff"
    private val paint = Paint(Paint.ANTI_ALIAS_FLAG)



    override fun onDraw(canvas: Canvas) {
        // call the super method to keep any drawing from the parent side.
        super.onDraw(canvas)
        createButton()
    }

    private fun createButton() {
        paint.color = R.drawable.search_shape
//        text.setTextColor(resources.getColor(R.color.black))
//        text.setBackgroundResource(R.drawable.search_shape)
//        paint.color =resources.getColor(background)
//        paint.style = Paint.Style.FILL
//
//        canvas.drc
    }







}
