package com.example.calculator.models

interface Token {
    var type: TokenType
    var value: String
}

class OperationToken(var operation: MathematicalOperation) : Token {
    override var type: TokenType = TokenType.OPERATION
    override var value: String = ""

    init {
        value = when(operation) {
            MathematicalOperation.ADD -> "+"
            MathematicalOperation.SUBTRACT -> "-"
            MathematicalOperation.MULTIPLY -> "x"
            MathematicalOperation.DIVIDE -> "/"
            else -> ""
        }
    }
}

class DigitToken(override var value: String) : Token {
    override var type: TokenType = TokenType.DIGIT
}

class DotToken() : Token {
    override var type: TokenType = TokenType.DOT
    override var value: String = "."
}

class SpaceToken() : Token {
    override var type: TokenType = TokenType.SPACE
    override var value: String = " "
}

class NullToken() : Token {
    override var type: TokenType = TokenType.NULL
    override var value: String = ""
}