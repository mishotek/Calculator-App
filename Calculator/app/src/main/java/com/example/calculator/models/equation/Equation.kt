package com.example.calculator.models.equation

import android.util.Log
import com.example.calculator.models.MathematicalOperation
import java.lang.Exception

class Equation {

    private val equationBuilder: EquationBuilder = EquationBuilder()

    fun appendOperation(op: MathematicalOperation) {
        equationBuilder.appendOperation(op)
    }

    fun appendDigit(digit: String) {
        equationBuilder.appendDigit(digit)
    }

    fun appendDot() {
        equationBuilder.appendDot()
    }

    fun pop() {
        equationBuilder.pop()
    }

    fun clear() {
        equationBuilder.clear()
    }

    fun calc(): String {
        return try {
            EquationParser(equationBuilder.getTokens()).parse()
        } catch(err: Exception) {
            Log.i("CALC_ERROR", err.toString())
            "NaN"
        }
    }

    override fun toString(): String {
        return equationBuilder.toString()
    }
}