package com.example.calculator.views.main

interface MainSceneContract {

    interface View {
        fun showEquation(equation: String)
        fun showResult(res: String)
    }

    interface Presenter {
        fun initCalculator()
        fun append(item: String)
        fun pop()
        fun calc()
        fun toggleTheme()
        fun serialize(): String
    }
}