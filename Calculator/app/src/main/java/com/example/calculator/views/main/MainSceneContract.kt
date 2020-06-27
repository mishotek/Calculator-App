package com.example.calculator.views.main

interface MainSceneContract {

    interface View {
        fun showEquation(equation: String)
        fun showResult(res: String)
    }

    interface Presenter {
        fun append(item: String)
        fun pop()
        fun calc()
        fun serialize(): String
    }
}