package com.example.vectorcalculations_android

import com.example.vectorcalculations_android.vectorcalculations.util.roundTo
import com.example.vectorcalculations_android.vectorcalculations.util.roundedTo
import com.example.vectorcalculations_android.vectorcalculations.vector.RealVectorCalculator
import com.example.vectorcalculations_android.vectorcalculations.vector.Vector
import junit.framework.TestCase

class RealVectorCalculatorTest : TestCase() {

    private lateinit var calculator: RealVectorCalculator
    private lateinit var v1: Vector
    private lateinit var v2: Vector

    public override fun setUp() {
        calculator =
            RealVectorCalculator()
        v1 = Vector(
            listOf(
                1.0,
                4.0,
                5.1
            )
        )
        v2 = Vector(
            listOf(
                2.0,
                8.0,
                10.2
            )
        )
    }

    fun testSum() {
        assertEquals(
            calculator.sum(v1, v2).roundedTo(1),
            Vector(
                listOf(
                    3.0,
                    12.0,
                    15.3
                )
            )
        )
    }

    fun testSubtract() {
        assertEquals(
            calculator.subtract(v1, v2).roundedTo(1),
            Vector(
                listOf(
                    -1.0,
                    -4.0,
                    -5.1
                )
            )
        )
    }

    fun testMultiply() {
        assertEquals(
            calculator.multiply(v1, 5.0),
            Vector(
                listOf(
                    5.0,
                    20.0,
                    25.5
                )
            )
        )
    }

    fun testMultiplyScalar() {
        assertEquals(calculator.multiplyScalar(v1, v2), 86.02)
    }

    fun testDivide() {
        assertEquals(
            calculator.divide(v1, 5.0),
            Vector(
                listOf(
                    0.2,
                    0.8,
                    1.02
                )
            )
        )
    }

    fun testCos() {
        assertEquals(calculator.cos(v1, v2).roundTo(1), 1.0)
    }

    fun testVectorsCollinearity() {
        assertEquals(
            calculator.vectorsCollinearity(v1, v2),
            com.example.vectorcalculations_android.vectorcalculations.vector.VectorsCollinearity.CO_DIRECTIONAL
        )
    }

    fun testVectorsEquals() {
        assertEquals(calculator.vectorsEquals(v1, v2), false)
    }

    fun testVectorsOrthogonal() {
        assertEquals(calculator.vectorsOrthogonal(v1, v2), false)
    }

    fun testProjectionScalar() {
        assertEquals(calculator.projectionScalar(v1, v2).roundTo(3), 6.558)
    }

    fun testProjectionVector() {
        assertEquals(
            calculator.projectionVector(v1, v2),
            Vector(
                listOf(
                    34.408,
                    137.632,
                    175.4808
                )
            )
        )
    }

    fun testVectorLength() {
        assertEquals(v1.length.roundTo(3), 6.558)
    }

    fun testNormalization() {
        assertEquals(
            Vector.createNormalized(
                v1.coordinates,
                v2.coordinates
            ), v1
        )
    }
}