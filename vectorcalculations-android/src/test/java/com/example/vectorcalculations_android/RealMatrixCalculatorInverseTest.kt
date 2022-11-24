package com.example.vectorcalculations_android

import com.example.vectorcalculations_android.vectorcalculations.matrix.Matrix
import com.example.vectorcalculations_android.vectorcalculations.matrix.RealMatrixCalculator
import com.example.vectorcalculations_android.vectorcalculations.util.roundedTo
import com.example.vectorcalculations_android.vectorcalculations.vector.RealVectorCalculator
import junit.framework.TestCase

class RealMatrixCalculatorInverseTest: TestCase() {
    private lateinit var calculator: RealMatrixCalculator
    public override fun setUp() {
        calculator = RealMatrixCalculator(RealVectorCalculator())
    }

    fun test1() = assertEquals(
        Matrix.fromDoubles(
            listOf(
                listOf(-2.0, 1.0),
                listOf(1.5, -0.5)
            )
        ),
        calculator.inverse(
            Matrix.fromDoubles(
                listOf(
                    listOf(1.0, 2.0),
                    listOf(3.0, 4.0)
                )
            )
        )
    )

    fun test2() = assertEquals(
        Matrix.fromDoubles(
            listOf(
                listOf(0.0, 2.0, -1.0),
                listOf(-1.0, 5.0, -3.0),
                listOf(-1.0, 8.0, -5.0),
            )
        ),
        calculator.inverse(
            Matrix.fromDoubles(
                listOf(
                    listOf(1.0, -2.0, 1.0),
                    listOf(2.0, 1.0, -1.0),
                    listOf(3.0, 2.0, -2.0),
                )
            )
        ).roundedTo(3)
    )
}