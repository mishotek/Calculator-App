package com.example.calculator.models

enum class MathematicalOperation {
    ADD, SUBTRACT, DIVIDE, MULTIPLY
}

class OperationLiterals {
    companion object {
        const val ADD = "+"
        const val SUBTRACT = "-"
        const val DIVIDE = "/"
        const val MULTIPLY = "x"
        const val DOT = "."
    }
}