package com.example.calculator.models.equation

import android.util.Log
import com.example.calculator.models.MathematicalOperation
import com.example.calculator.models.OperationLiterals
import java.lang.Exception

class Equation {

    private val equationBuilder: EquationBuilder = EquationBuilder()

    constructor() {}

    constructor(serialized: String?) {
        if (serialized != null) {
            for (c in serialized) {
                append("" + c)
            }
        }
    }

    fun append(item: String) {
        when (item) {
            OperationLiterals.DOT -> {
                appendDot()
            }
            OperationLiterals.ADD -> {
                appendOperation(MathematicalOperation.ADD)
            }
            OperationLiterals.SUBTRACT -> {
                appendOperation(MathematicalOperation.SUBTRACT)
            }
            OperationLiterals.MULTIPLY -> {
                appendOperation(MathematicalOperation.MULTIPLY)
            }
            OperationLiterals.DIVIDE -> {
                appendOperation(MathematicalOperation.DIVIDE)
            }
            else -> {
                appendDigit(item)
            }
        }
    }

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

    fun serialize(): String {
        return toString().replace(" ", "")
    }
}