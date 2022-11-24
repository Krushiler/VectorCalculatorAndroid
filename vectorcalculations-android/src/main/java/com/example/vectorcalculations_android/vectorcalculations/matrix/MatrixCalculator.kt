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

    fun inverse(m: Matrix): Matrix
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

    override fun inverse(m: Matrix): Matrix {
        var matrix = extend(m, m.createFromEach { _, row, column ->
            if (row == column) 1.0
            else 0.0
        })
        matrix = findInverseMatrixForLowerHalf(matrix)
        matrix = getReversedMatrix(matrix)
        matrix = findInverseMatrixForLowerHalf(matrix, lastRow = matrix.rows.size - 1)
        matrix = getReversedMatrix(matrix)

        val resultMatrixCords = mutableListOf<List<Double>>()
        for (row in matrix.rows) {
            val cords = mutableListOf<Double>()
            for (columnIndex in matrix.columns.size / 2 until matrix.columns.size) {
                cords.add(row[columnIndex])
            }
            resultMatrixCords.add(cords)
        }
        return Matrix.fromDoubles(resultMatrixCords)
    }

    private fun findInverseMatrixForLowerHalf(m: Matrix, lastRow: Int = m.rows.size): Matrix {
        var inverse = m

        for (i in 0 until lastRow) {
            val multiplier = inverse[i][i]
            inverse = inverse.setRow(
                i, vectorCalculator.multiply(
                    inverse[i], 1 / multiplier
                )
            )
            for (j in i + 1 until m.rows.size) {
                val first = vectorCalculator.multiply(inverse[i], inverse[j][i])
                inverse = inverse.setRow(
                    j, vectorCalculator.subtract(
                        inverse[j], first
                    )
                )
            }
            inverse = sortInverseMatrix(inverse)
        }
        return inverse
    }

    private fun getReversedMatrix(m: Matrix): Matrix {
        val rowsCount = m.rows.size
        var result = m
        for (i in 0 until rowsCount / 2) {
            result = result.swapRows(i, rowsCount - 1 - i)
        }
        for (i in 0 until rowsCount / 2) {
            result = result.swapColumns(i, rowsCount - 1 - i)
        }
        return result
    }

    private fun sortInverseMatrix(m: Matrix): Matrix = Matrix(m.rows.sortedBy {
        var zeros = 0
        for (i in 0 until it.coordinates.size / 2) {
            if (it.coordinates[i] == 0.0) {
                zeros++
            } else {
                return@sortedBy zeros
            }
        }
        return@sortedBy zeros
    })
}