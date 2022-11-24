package com.example.vectorcalculations_android

import com.example.vectorcalculations_android.vectorcalculations.matrix.Matrix
import com.example.vectorcalculations_android.vectorcalculations.matrix.RealMatrixCalculator
import com.example.vectorcalculations_android.vectorcalculations.sole.GaussISoleCalculator
import com.example.vectorcalculations_android.vectorcalculations.sole.ISoleCalculator
import com.example.vectorcalculations_android.vectorcalculations.sole.InverseMatrixSoleCalculator
import com.example.vectorcalculations_android.vectorcalculations.vector.RealVectorCalculator
import junit.framework.TestCase

class SoleCalculatorTest : TestCase() {
    private lateinit var calculator: ISoleCalculator

    private val gaussSoleCalculator = GaussISoleCalculator(RealMatrixCalculator(RealVectorCalculator()), RealVectorCalculator())
    private val inverseMatrixSoleCalculator = InverseMatrixSoleCalculator(RealMatrixCalculator(RealVectorCalculator()), RealVectorCalculator())

    public override fun setUp() {
        calculator = inverseMatrixSoleCalculator
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
            listOf(-4.0, 5.0),
            calculator.solveSole(
                Matrix.fromDoubles(
                    listOf(
                        listOf(1.0, 2.0),
                        listOf(3.0, 4.0),
                    )
                ),
                Matrix.fromDoubles(
                    listOf(
                        listOf(6.0),
                        listOf(8.0),
                    )
                )
            )
        )
    }
}