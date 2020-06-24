package com.example.calculator.components

import android.content.Context
import android.util.AttributeSet
import android.view.View
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import com.example.calculator.R

class CalcButton(context: Context?, attrs: AttributeSet?): ConstraintLayout(context, attrs) {
    private var textView: TextView? = null

    init {
        View.inflate(context, R.layout.calc_button, this)

        textView = findViewById(R.id.buttonText)

        context?.theme?.obtainStyledAttributes(attrs, R.styleable.CalcButton, 0, 0)?.apply {
            val text = getString(R.styleable.CalcButton_buttonText)
            val color = getColor(R.styleable.CalcButton_buttonColor, resources.getColor(R.color.cardview_light_background))

            if (text != null) {
                textView?.text = text
            }

            textView?.setTextColor(color)
        }
    }

}
