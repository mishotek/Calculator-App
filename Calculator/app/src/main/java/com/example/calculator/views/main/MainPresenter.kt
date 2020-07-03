package com.example.calculator.views.main
import android.app.Activity
import com.example.calculator.models.Theme
import com.example.calculator.models.equation.Equation

class MainPresenter(var view: MainSceneContract.View, serialized: String?) : MainSceneContract.Presenter {

    private val equation: Equation = Equation(serialized)
    private val theme: Theme = Theme(view as Activity)

    init {
        theme.applyTheme()
    }

    override fun initCalculator() {
        updateView()
    }

    override fun append(item: String) {
        equation.append(item)
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

    override fun toggleTheme() {
        theme.toggleTheme()
    }

    override fun serialize(): String {
        return equation.serialize()
    }

    private fun updateView() {
        view.showEquation(equation.toString())
        view.showResult(equation.calc())
    }
}