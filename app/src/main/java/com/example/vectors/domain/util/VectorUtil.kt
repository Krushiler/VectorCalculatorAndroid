package com.example.vectors.domain.util

import com.example.vectors.domain.vector.Vector

fun ensureVectorsHaveSameDimens(v1: Vector, v2: Vector) {
    if (v1.dimensions != v2.dimensions) throw IncorrectDimensionException("Vectors must have the same dimensions")
}

inline fun Vector.createFromEach(action: (index: Int) -> Double): Vector {
    val cords = mutableListOf<Double>()
    for (i in coordinates.indices) {
        cords.add(action.invoke(i))
    }
    return Vector(cords)
}

inline fun Vector.applyForEach(action: (index: Int) -> Unit) {
    for (i in coordinates.indices) {
        action.invoke(i)
    }
}