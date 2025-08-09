package com.example.ci_cd.domain

import com.plcoding.materialcalculator.domain.ExpressionParser
import com.plcoding.materialcalculator.domain.ExpressionPart
import com.plcoding.materialcalculator.domain.Operation
import com.plcoding.materialcalculator.domain.ParenthesesType
import org.junit.Assert.*

import org.junit.Test

class ExpressionParserTest {

    private lateinit var parser: ExpressionParser

    @Test
    fun `Simple expression is properly parsed`() {

        // Given
        parser = ExpressionParser("3+5-3x4/3")

        // Act
        val parts = parser.parse()

        // Assertion
        val expexted = listOf(
            ExpressionPart.Number(3.0),
            ExpressionPart.Op(Operation.ADD),
            ExpressionPart.Number(5.0),
            ExpressionPart.Op(Operation.SUBTRACT),
            ExpressionPart.Number(3.0),
            ExpressionPart.Op(Operation.MULTIPLY),
            ExpressionPart.Number(4.0),
            ExpressionPart.Op(Operation.DIVIDE),
            ExpressionPart.Number(3.0),
        )
        assertEquals(expexted, parts)
    }

    @Test
    fun `Expression with parentheses is properly parsed`() {
        parser = ExpressionParser("4-(4x5)")
        val actually = parser.parse()

        val expected = listOf(
            ExpressionPart.Number(4.0),
            ExpressionPart.Op(Operation.SUBTRACT),
            ExpressionPart.Parentheses(ParenthesesType.Opening),
            ExpressionPart.Number(4.0),
            ExpressionPart.Op(Operation.MULTIPLY),
            ExpressionPart.Number(5.0),
            ExpressionPart.Parentheses(ParenthesesType.Closing),
        )
        assertEquals(expected, actually)
    }

//    @Test
//    fun `Expression with unsupported character throws exception`() {
//        val exception = assertThrows(IllegalArgumentException::class.java) {
//            parser = ExpressionParser("2+3a")
//            parser.parse()
//        }
//        assertEquals("IllegalArgumentException", exception.message)
//    }

}