package com.example.vectorcalculations_android

import com.example.vectorcalculations_android.vectorcalculations.matrix.Matrix
import com.example.vectorcalculations_android.vectorcalculations.matrix.RealMatrixCalculator
import com.example.vectorcalculations_android.vectorcalculations.vector.RealVectorCalculator
import com.example.vectorcalculations_android.vectorcalculations.vector.Vector
import junit.framework.TestCase

class RealMatrixCalculatorTest : TestCase() {

    private lateinit var calculator: RealMatrixCalculator
    private lateinit var m1: Matrix
    private lateinit var m2: Matrix

    public override fun setUp() {
        calculator = RealMatrixCalculator(RealVectorCalculator())
        m1 = Matrix(
            listOf(
                Vector(listOf(4.0, 2.0)),
                Vector(listOf(9.0, 0.0))
            )
        )
        m2 = Matrix(
            listOf(
                Vector(listOf(3.0, 1.0)),
                Vector(listOf(-3.0, 4.0))
            )
        )
    }

    fun testSum() {
        assertEquals(
            calculator.sum(m1, m2),
            Matrix(
                listOf(
                    Vector(listOf(7.0, 3.0)),
                    Vector(listOf(6.0, 4.0))
                )
            )
        )
    }

    fun testSubtract() {
        assertEquals(
            calculator.sum(m1, m2),
            Matrix(
                listOf(
                    Vector(listOf(7.0, 3.0)),
                    Vector(listOf(6.0, 4.0))
                )
            )
        )
    }

    fun testMultiply() {
        assertEquals(
            calculator.sum(m1, m2),
            Matrix(
                listOf(
                    Vector(listOf(7.0, 3.0)),
                    Vector(listOf(6.0, 4.0))
                )
            )
        )
    }

    fun testMultiplyScalar() {
        assertEquals(
            calculator.multiplyScalar(m1, m2),
            Matrix(
                listOf(
                    Vector(listOf(6.0, 12.0)),
                    Vector(listOf(27.0, 9.0))
                )
            )
        )
    }

    fun testMultiplyRow() {
        assertEquals(
            calculator.sum(m1, m2),
            Matrix(
                listOf(
                    Vector(listOf(7.0, 3.0)),
                    Vector(listOf(6.0, 4.0))
                )
            )
        )
    }

    fun testTransposed() {
        assertEquals(
            m1.transposed(),
            Matrix(
                listOf(
                    Vector(listOf(4.0, 9.0)),
                    Vector(listOf(2.0, 0.0))
                )
            )
        )
    }

    fun testSwapRows() {
        assertEquals(
            m1.swapRows(0, 1),
            Matrix(
                listOf(
                    Vector(listOf(9.0, 0.0)),
                    Vector(listOf(4.0, 2.0))
                )
            )
        )
    }

    fun testSwapColumns() {
        assertEquals(
            m1.swapColumns(0, 1),
            Matrix(
                listOf(
                    Vector(listOf(2.0, 4.0)),
                    Vector(listOf(0.0, 9.0))
                )
            )
        )
    }
}