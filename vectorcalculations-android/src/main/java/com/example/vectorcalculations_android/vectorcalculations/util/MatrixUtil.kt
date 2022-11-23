package com.example.vectorcalculations_android.vectorcalculations.util

import com.example.vectorcalculations_android.vectorcalculations.matrix.Matrix
import com.example.vectorcalculations_android.vectorcalculations.vector.Vector

inline fun Matrix.createFromEach(action: (item: Double, row: Int, column: Int) -> Double): Matrix {
    val newRows = mutableListOf<Vector>()
    for (row in rows.indices) {
        val cords = mutableListOf<Double>()
        for (column in rows[row].coordinates.indices) {
            cords.add(action.invoke(rows[row].coordinates[column], row, column))
        }
        newRows.add(Vector(cords))
    }
    return Matrix(newRows)
}

fun Matrix.roundedTo(decimals: Int): Matrix {
    val vectors = mutableListOf<Vector>()
    rows.forEach { v->
        vectors.add(v.roundedTo(decimals))
    }
    return Matrix(vectors)
}