package com.example.vectorcalculations_android.vectorcalculations.vector

import com.example.vectorcalculations_android.vectorcalculations.util.applyForEach
import com.example.vectorcalculations_android.vectorcalculations.util.createFromEach
import com.example.vectorcalculations_android.vectorcalculations.util.ensureVectorsHaveSameDimens
import kotlin.math.abs
import kotlin.math.acos

interface VectorCalculator {
    fun sum(v1: Vector, v2: Vector): Vector
    fun subtract(v1: Vector, v2: Vector): Vector

    fun     multiply(v1: Vector, n: Double): Vector
    fun multiplyScalar(v1: Vector, v2: Vector): Double

    fun divide(v1: Vector, n: Double): Vector

    fun cos(v1: Vector, v2: Vector): Double
    fun angleDegrees(v1: Vector, v2: Vector): Double

    fun vectorsCollinearity(v1: Vector, v2: Vector, maxError: Int = 0): VectorsCollinearity
    fun vectorsEquals(v1: Vector, v2: Vector, maxError: Int = 0): Boolean
    fun vectorsOrthogonal(v1: Vector, v2: Vector): Boolean

    fun projectionScalar(v1: Vector, v2: Vector): Double
    fun projectionVector(v1: Vector, v2: Vector): Vector
}

class RealVectorCalculator : VectorCalculator {

    override fun sum(v1: Vector, v2: Vector): Vector {
        ensureVectorsHaveSameDimens(v1, v2)
        return v1.createFromEach { index ->
            v1.coordinates[index] + v2.coordinates[index]
        }
    }

    override fun subtract(v1: Vector, v2: Vector): Vector {
        ensureVectorsHaveSameDimens(v1, v2)
        return v1.createFromEach { index ->
            v1.coordinates[index] - v2.coordinates[index]
        }
    }

    override fun multiply(v1: Vector, n: Double): Vector {
        return v1.createFromEach { index ->
            v1.coordinates[index] * n
        }
    }

    override fun multiplyScalar(v1: Vector, v2: Vector): Double {
        ensureVectorsHaveSameDimens(v1, v2)
        var sum = 0.0
        v1.applyForEach { index ->
            sum += v1.coordinates[index] * v2.coordinates[index]
        }
        return sum
    }

    override fun divide(v1: Vector, n: Double): Vector {
        return v1.createFromEach { index ->
            v1.coordinates[index] / n
        }
    }

    override fun cos(v1: Vector, v2: Vector): Double {
        ensureVectorsHaveSameDimens(v1, v2)
        return multiplyScalar(v1, v2) / (v1.length * v2.length)
    }

    override fun angleDegrees(v1: Vector, v2: Vector): Double {
        return Math.toDegrees(acos(cos(v1, v2)))
    }

    override fun vectorsCollinearity(v1: Vector, v2: Vector, maxError: Int): VectorsCollinearity {
        val ratio: Double = v2.coordinates[0] / v1.coordinates[0]
        v2.coordinates.forEachIndexed { index, _ ->
            val curRatio = v2.coordinates[index] / v1.coordinates[index]
            if (curRatio < ratio - index || curRatio > ratio + index) return VectorsCollinearity.NOT_COLLINEAR
        }
        if (ratio > 0) return VectorsCollinearity.CO_DIRECTIONAL
        return VectorsCollinearity.OPPOSITE
    }

    override fun vectorsEquals(v1: Vector, v2: Vector, maxError: Int): Boolean {
        return abs(v2.length - v1.length) <= maxError && vectorsCollinearity(
            v1,
            v2
        ) == VectorsCollinearity.CO_DIRECTIONAL
    }

    override fun vectorsOrthogonal(v1: Vector, v2: Vector): Boolean {
        return multiplyScalar(v1, v2) == 0.0
    }

    override fun projectionScalar(v1: Vector, v2: Vector): Double {
        return multiplyScalar(v1, v2) / v2.length
    }

    override fun projectionVector(v1: Vector, v2: Vector): Vector {
        var v2Square = 0.0
        v2.applyForEach {
            v2Square += it * it
        }
        return multiply(v2, multiplyScalar(v1, v2) / v2Square)
    }
}