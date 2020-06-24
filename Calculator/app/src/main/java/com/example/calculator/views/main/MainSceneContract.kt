package com.example.calculator.views.main

import com.example.calculator.models.MathematicalOperation

interface MainSceneContract {

    interface View {
        fun showEquation(equation: String)
        fun showResult(res: String)
    }

    interface Presenter {
        fun appendOperation(op: MathematicalOperation)
        fun appendDigit(digit: String)
        fun appendDot()
        fun pop()
        fun calc()
    }
}