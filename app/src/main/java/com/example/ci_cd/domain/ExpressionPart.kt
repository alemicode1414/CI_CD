package com.example.ci_cd.domain

sealed interface ExpressionPart {
    data class Number(val number: Double) : ExpressionPart
    data class Op(val operation: Operation) : ExpressionPart
    data class Parentheses(val isOpening: ParenthesisType) : ExpressionPart
}

sealed interface ParenthesisType {
    data object Opening : ParenthesisType
    data object Closing : ParenthesisType
}