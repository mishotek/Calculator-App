package com.example.calculator.views.main

import com.example.calculator.models.*
import com.example.calculator.models.equation.Equation
import com.example.calculator.models.equation.EquationBuilder

class MainPresenter(var view: MainSceneContract.View) : MainSceneContract.Presenter {

    private val equation: Equation = Equation()

    init {
        updateView()
    }

    override fun appendOperation(op: MathematicalOperation) {
        equation.appendOperation(op)
        updateView()
    }

    override fun appendDigit(digit: String) {
        equation.appendDigit(digit)
        updateView()
    }

    override fun appendDot() {
        equation.appendDot()
        updateView()
    }

    override fun pop() {
        equation.pop()
        updateView()
    }

    override fun calc() {
        val res = equation.calc()
        equation.clear()
        equation.appendDigit(res)

        view.showEquation(res)
        view.showResult("")
    }

    private fun updateView() {
        view.showEquation(equation.toString())
        view.showResult(equation.calc())
    }
}