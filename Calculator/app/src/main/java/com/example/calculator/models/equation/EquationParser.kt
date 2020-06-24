package com.example.calculator.models.equation

import com.example.calculator.models.DigitToken
import com.example.calculator.models.SpaceToken
import com.example.calculator.models.Token
import com.example.calculator.models.TokenType
import java.lang.Double.parseDouble
import java.util.*
import kotlin.Exception
import kotlin.collections.ArrayList

class EquationParser (private val tokens: ArrayList<Token>) {

    fun parse(): String {
        if (tokens.isEmpty()) {
            return ""
        }

        // If last token is operation add 0 in the end to make
        // equation valid
        makeSafe(tokens)

        val argQueue =  ArgQueue(tokens)

        if (argQueue.isEmpty() || !isNumber(argQueue.top())) {
            throw Exception("Equation must start with number")
        }

        val stack = Stack<String>()
        stack.push(argQueue.pop())

        while (!argQueue.isEmpty()) {
            if (!isOperation(argQueue.top())) {
                throw Exception("Operation expected after number")
            }

            val operation: String = argQueue.pop()

            if (argQueue.isEmpty()) {
                throw Exception("Equation must end with number")
            }

            if (!isNumber(argQueue.top())) {
                throw Exception("Number expected after operation")
            }

            val num2: String = argQueue.pop()

            if (operation == "+" || operation == "-") {
                stack.push(operation)
                stack.push(num2)
            } else {
                val num1 = stack.pop()

                when (operation) {
                    "x" -> {
                        stack.push(mul(num1, num2))
                    }
                    "/" -> {
                        stack.push(div(num1, num2))
                    }
                    else -> {
                        throw Exception("Unexpected token $operation")
                    }
                }
            }
        }

        while (stack.size > 1) {
            val num2 = stack.pop()
            val op = stack.pop()
            val num1 = stack.pop()


            when (op) {
                "+" -> {
                    stack.push(add(num1, num2))
                }
                "-" -> {
                    stack.push(sub(num1, num2))
                }
                else -> {
                    throw Exception("Operator expected got $op")
                }
            }
        }

        return stack.pop()
    }

    private fun makeSafe(tokens: ArrayList<Token>) {
        if (tokens.isEmpty()) {
            return
        }

        if (tokens.size > 1 && tokens[tokens.size - 1].type == TokenType.OPERATION) {
            tokens.add(SpaceToken())
            tokens.add(DigitToken("0"))
            return
        }

        if (tokens.size > 2 && tokens[tokens.size - 1].type == TokenType.SPACE && tokens[tokens.size - 2].type == TokenType.OPERATION) {
            tokens.add(DigitToken("0"))
            return
        }

        if (tokens.size > 1 && tokens[tokens.size - 1].type == TokenType.DOT) {
            tokens.add(DigitToken("0"))
            return
        }
    }

    private fun isNumber(str: String): Boolean {
        return str.isNotEmpty() && str.matches("-?\\d+(\\.\\d+)?".toRegex())
    }

    private fun isOperation(str: String): Boolean {
        return str == "+" || str == "-" || str == "x" || str == "/"
    }

    private fun mul(num1: String, num2: String): String {
        val res = parseDouble(num1) * parseDouble(num2)
        return "$res"
    }

    private fun div(num1: String, num2: String): String {
        val res = parseDouble(num1) / parseDouble(num2)
        return "$res"
    }

    private fun add(num1: String, num2: String): String {
        val res = parseDouble(num1) + parseDouble(num2)
        return "$res"
    }

    private fun sub(num1: String, num2: String): String {
        val res = parseDouble(num1) - parseDouble(num2)
        return "$res"
    }

    class ArgQueue (private val tokens: List<Token>) {

        private var args: List<String>
        private var index = 0

        init {
            args =  tokens
                .map { token -> token.value}
                .reduce { equation, value -> equation + value }
                .split(' ')
                .filter { str -> str.isNotEmpty() }
        }

        fun isEmpty(): Boolean {
            return index >= args.size
        }

        fun pop(): String {
            if (isEmpty()) {
                return ""
            }

            return args[index++]
        }

        fun top(): String {
            if (isEmpty()) {
                return ""
            }

            return args[index]
        }

        override fun toString(): String {
            return args.subList(index - 1, args.size - 1).toString()
        }
    }
}