package com.example.calculator.models.equation

import com.example.calculator.models.*

class EquationBuilder {

    private val tokens: ArrayList<Token> = ArrayList()

    fun appendOperation(op: MathematicalOperation) {
        if (getLastToken().type == TokenType.OPERATION) {
            popArray()
        }

        if (getLastToken().type == TokenType.DOT) {
            tokens.add(DigitToken("0"))
        }

        if (getLastToken().type != TokenType.SPACE && getLastToken().type != TokenType.NULL) {
            tokens.add(SpaceToken())
        }

        tokens.add(OperationToken(op))
    }

    fun appendDigit(digit: String) {
        if (getLastToken().type == TokenType.OPERATION) {
            tokens.add(SpaceToken())
        }

        tokens.add(DigitToken(digit))
    }

    fun appendDot() {
        if (getLastToken().type != TokenType.DIGIT) {
            appendDigit("0")
        }

        tokens.add(DotToken())
    }

    fun pop() {
        do {
            popArray()
        } while (getLastToken().type == TokenType.SPACE)
    }

    fun clear() {
        tokens.clear()
    }

    fun getTokens(): ArrayList<Token> {
        return ArrayList(tokens)
    }

    override fun toString(): String {
        if (tokens.isEmpty()) {
            return "";
        }

        return tokens
            .map { token -> token.value }
            .reduce { equation, value -> "" + equation + value }
    }

    private fun popArray() {
        if (tokens.isNotEmpty()) {
            tokens.removeAt(tokens.size - 1)
        }
    }

    private fun getLastToken(): Token {
        if (tokens.isEmpty()) {
            return NullToken()
        }

        return tokens[tokens.size - 1]
    }
}