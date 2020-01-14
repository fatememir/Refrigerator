package com.example.refrigerator.views;

import android.content.Context;
import android.graphics.Canvas;
import android.util.AttributeSet;
import android.widget.Button;

import androidx.appcompat.widget.AppCompatButton;

public class View extends AppCompatButton {

    private static final String TAG = View.class.getSimpleName();

    private Context context;


    public View(Context context) {
        super(context);
    }


    public View(Context context, AttributeSet attrs) {
        super(context, attrs);
    }


    public View(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }


    private void init(Context context){
        this.context = context;
    }


    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

    }

}
