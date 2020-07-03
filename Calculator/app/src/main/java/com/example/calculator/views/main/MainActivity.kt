package com.example.calculator.views.main

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Window
import android.view.WindowManager
import android.widget.TextView
import com.example.calculator.R
import com.example.calculator.components.CalcButton
import com.example.calculator.models.MathematicalOperation
import com.example.calculator.models.OperationLiterals
import org.w3c.dom.Text

class MainActivity : AppCompatActivity(), MainSceneContract.View {

    private val STATE_KEY = "equationStr"
    private lateinit var presenter: MainSceneContract.Presenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        supportActionBar?.hide()

        val serializedPresenter = savedInstanceState?.getString(STATE_KEY)
        presenter = MainPresenter(this, serializedPresenter)

        setContentView(R.layout.activity_main)

        presenter.initCalculator()

        addClickListeners()
        addThemeChangeListener()
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putString(STATE_KEY, presenter.serialize())
    }

    override fun showEquation(equation: String) {
        findViewById<TextView>(R.id.equation)?.text = equation
    }

    override fun showResult(res: String) {
        findViewById<TextView>(R.id.result)?.text = res
    }

    private fun addClickListeners() {
        // Listen to clear
        findViewById<CalcButton>(R.id.calcButtonAc)?.setOnClickListener { presenter.pop() }

        // Listen to operations
        findViewById<CalcButton>(R.id.calcButtonDiv)?.setOnClickListener { presenter.append(OperationLiterals.DIVIDE) }
        findViewById<CalcButton>(R.id.calcButtonMul)?.setOnClickListener { presenter.append(OperationLiterals.MULTIPLY) }
        findViewById<CalcButton>(R.id.calcButtonSub)?.setOnClickListener { presenter.append(OperationLiterals.SUBTRACT) }
        findViewById<CalcButton>(R.id.calcButtonAdd)?.setOnClickListener { presenter.append(OperationLiterals.ADD) }

        // Listen to dot
        findViewById<CalcButton>(R.id.calcButtonDot)?.setOnClickListener { presenter.append(OperationLiterals.DOT) }

        // Listen to calc
        findViewById<CalcButton>(R.id.calcButtonEq)?.setOnClickListener { presenter.calc() }

        // Listen to numbers
        findViewById<CalcButton>(R.id.calcButtonNum0)?.setOnClickListener { presenter.append("0") }
        findViewById<CalcButton>(R.id.calcButtonNum1)?.setOnClickListener { presenter.append("1") }
        findViewById<CalcButton>(R.id.calcButtonNum2)?.setOnClickListener { presenter.append("2") }
        findViewById<CalcButton>(R.id.calcButtonNum3)?.setOnClickListener { presenter.append("3") }
        findViewById<CalcButton>(R.id.calcButtonNum4)?.setOnClickListener { presenter.append("4") }
        findViewById<CalcButton>(R.id.calcButtonNum5)?.setOnClickListener { presenter.append("5") }
        findViewById<CalcButton>(R.id.calcButtonNum6)?.setOnClickListener { presenter.append("6") }
        findViewById<CalcButton>(R.id.calcButtonNum7)?.setOnClickListener { presenter.append("7") }
        findViewById<CalcButton>(R.id.calcButtonNum8)?.setOnClickListener { presenter.append("8") }
        findViewById<CalcButton>(R.id.calcButtonNum9)?.setOnClickListener { presenter.append("9") }
    }

    private fun addThemeChangeListener() {
        findViewById<TextView>(R.id.themeSwitch)?.setOnClickListener {
            presenter.toggleTheme()
        }
    }
}