package com.example.calculator.views.main

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import com.example.calculator.R
import com.example.calculator.components.CalcButton
import com.example.calculator.models.MathematicalOperation

class MainActivity : AppCompatActivity(), MainSceneContract.View {

    private lateinit var presenter: MainSceneContract.Presenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        presenter = MainPresenter(this)
        addClickListeners()
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
        findViewById<CalcButton>(R.id.calcButtonDiv)?.setOnClickListener { presenter.appendOperation(MathematicalOperation.DIVIDE) }
        findViewById<CalcButton>(R.id.calcButtonMul)?.setOnClickListener { presenter.appendOperation(MathematicalOperation.MULTIPLY) }
        findViewById<CalcButton>(R.id.calcButtonSub)?.setOnClickListener { presenter.appendOperation(MathematicalOperation.SUBTRACT) }
        findViewById<CalcButton>(R.id.calcButtonAdd)?.setOnClickListener { presenter.appendOperation(MathematicalOperation.ADD) }

        // Listen to dot
        findViewById<CalcButton>(R.id.calcButtonDot)?.setOnClickListener { presenter.appendDot() }

        // Listen to calc
        findViewById<CalcButton>(R.id.calcButtonEq)?.setOnClickListener { presenter.calc() }

        // Listen to numbers
        findViewById<CalcButton>(R.id.calcButtonNum0)?.setOnClickListener { presenter.appendDigit("0") }
        findViewById<CalcButton>(R.id.calcButtonNum1)?.setOnClickListener { presenter.appendDigit("1") }
        findViewById<CalcButton>(R.id.calcButtonNum2)?.setOnClickListener { presenter.appendDigit("2") }
        findViewById<CalcButton>(R.id.calcButtonNum3)?.setOnClickListener { presenter.appendDigit("3") }
        findViewById<CalcButton>(R.id.calcButtonNum4)?.setOnClickListener { presenter.appendDigit("4") }
        findViewById<CalcButton>(R.id.calcButtonNum5)?.setOnClickListener { presenter.appendDigit("5") }
        findViewById<CalcButton>(R.id.calcButtonNum6)?.setOnClickListener { presenter.appendDigit("6") }
        findViewById<CalcButton>(R.id.calcButtonNum7)?.setOnClickListener { presenter.appendDigit("7") }
        findViewById<CalcButton>(R.id.calcButtonNum8)?.setOnClickListener { presenter.appendDigit("8") }
        findViewById<CalcButton>(R.id.calcButtonNum9)?.setOnClickListener { presenter.appendDigit("9") }
    }
}