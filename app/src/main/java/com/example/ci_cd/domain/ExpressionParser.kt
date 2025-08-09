package com.example.ci_cd.domain

class ExpressionParser constructor(
    private val calculation: String
) {
    fun parse(): List<ExpressionPart> {
        val result = mutableListOf<ExpressionPart>()
        var i = 0
        while (i < calculation.length) {
            val curChar = calculation[i]
            when {
                curChar in operationSymbols -> {
                    result.add(
                        ExpressionPart.Opt(operationFromSymbol(curChar))
                    )
                }

                curChar.isDigit() -> {
                    i = parseNumber(i, result)
                    continue
                }

                curChar in "()" -> {
                    result.add(
                        ExpressionPart.Parentheses(if (curChar == '(') ParenthesisType.Opening else ParenthesisType.Closing)
                    )
                }
                else -> throw IllegalArgumentException("IllegalArgumentException")
            }
            i++
        }

        return result
    }

    private fun parseNumber(startingIndex: Int, result: MutableList<ExpressionPart>): Int {

        var i = startingIndex
        val numberAsString = buildString {
            while (i < calculation.length && calculation[i] in "0123456789.") {
                append(calculation[i])
                i++
            }
        }
        result.add(ExpressionPart.Number(number = numberAsString.toDouble()))
        return i
    }
}