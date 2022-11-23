package com.example.vectorcalculations_android.vectorcalculations.matrix

import com.example.vectorcalculations_android.vectorcalculations.util.createFromEach
import com.example.vectorcalculations_android.vectorcalculations.vector.Vector
import com.example.vectorcalculations_android.vectorcalculations.vector.VectorCalculator

interface MatrixCalculator {
    fun sum(m1: Matrix, m2: Matrix): Matrix
    fun subtract(m1: Matrix, m2: Matrix): Matrix

    fun multiply(m1: Matrix, n: Double): Matrix
    fun multiplyScalar(m1: Matrix, m2: Matrix): Matrix
    fun multiplyRow(m1: Matrix, row: Int, n: Double): Matrix

    fun extend(m1: Matrix, m2: Matrix): Matrix
}

class RealMatrixCalculator(private val vectorCalculator: VectorCalculator) : MatrixCalculator {
    override fun sum(m1: Matrix, m2: Matrix): Matrix {
        val rows = mutableListOf<Vector>()
        for (i in m1.rows.indices) {
            rows.add(vectorCalculator.sum(m1.rows[i], m2.rows[i]))
        }
        return Matrix(rows)
    }

    override fun subtract(m1: Matrix, m2: Matrix): Matrix {
        return m1.createFromEach { _, row, column ->
            m1.rows[row].coordinates[column] * m2.columns[row].coordinates[column]
        }
    }

    override fun multiply(m1: Matrix, n: Double): Matrix {
        return m1.createFromEach { it, _, _ ->
            it * n
        }
    }

    override fun multiplyScalar(m1: Matrix, m2: Matrix): Matrix {
        val rows = mutableListOf<Vector>()
        for (i in m1.rows) {
            val row = mutableListOf<Double>()
            for (j in m2.columns) {
                var value = 0.0
                for (k in m1.columns.indices) {
                    value += i.coordinates[k] * j.coordinates[k]
                }
                row.add(value)
            }
            rows.add(Vector(row))
        }
        return Matrix(rows)
    }

    override fun multiplyRow(m1: Matrix, row: Int, n: Double): Matrix {
        val newRows = m1.rows.toMutableList()
        val newRow = vectorCalculator.multiply(newRows[row], n)
        newRows[row] = newRow
        return Matrix(newRows)
    }

    override fun extend(m1: Matrix, m2: Matrix): Matrix {
        val extendedRows = mutableListOf<Vector>()
        for (i in m1.rows.indices) {
            val vec = mutableListOf<Double>()
            vec.addAll(m1[i].coordinates)
            vec.addAll(m2[i].coordinates)
            extendedRows.add(Vector(vec))
        }
        return Matrix(extendedRows)
    }

}