package com.example.vectorcalculations_android

import com.example.vectorcalculations_android.vectorcalculations.matrix.Matrix
import com.example.vectorcalculations_android.vectorcalculations.matrix.RealMatrixCalculator
import com.example.vectorcalculations_android.vectorcalculations.sole.RealSoleCalculator
import com.example.vectorcalculations_android.vectorcalculations.vector.RealVectorCalculator
import junit.framework.TestCase

class RealSoleCalculatorTest : TestCase() {
    private lateinit var calculator: RealSoleCalculator

    public override fun setUp() {
        calculator =
            RealSoleCalculator(RealMatrixCalculator(RealVectorCalculator()), RealVectorCalculator())
    }

    fun test3dSole() {
        assertEquals(
            listOf(-7.0, -2.0, 2.0),
            calculator.solveSole(
                Matrix.fromDoubles(
                    listOf(
                        listOf(-1.0, 2.0, 6.0),
                        listOf(3.0, -6.0, 0.0),
                        listOf(1.0, 0.0, 6.0),
                    )
                ),
                Matrix.fromDoubles(
                    listOf(
                        listOf(15.0),
                        listOf(-9.0),
                        listOf(5.0),
                    )
                )
            )
        )
    }

    fun test2dSole() {
        assertEquals(
            listOf(2.5, -1.0),
            calculator.solveSole(
                Matrix.fromDoubles(
                    listOf(
                        listOf(2.0, 3.0),
                        listOf(4.0, 3.0),
                    )
                ),
                Matrix.fromDoubles(
                    listOf(
                        listOf(2.0),
                        listOf(7.0),
                    )
                )
            )
        )
    }
}