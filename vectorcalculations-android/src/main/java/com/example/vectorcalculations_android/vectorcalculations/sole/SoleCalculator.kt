package com.example.vectorcalculations_android.vectorcalculations.sole

import com.example.vectorcalculations_android.vectorcalculations.matrix.Matrix
import com.example.vectorcalculations_android.vectorcalculations.matrix.MatrixCalculator
import com.example.vectorcalculations_android.vectorcalculations.vector.Vector
import com.example.vectorcalculations_android.vectorcalculations.vector.VectorCalculator

interface SoleCalculator {
    fun solveSole(a: Matrix, b: Matrix): List<Double>
}

class RealSoleCalculator(
    private val matrixCalculator: MatrixCalculator,
    private val vectorCalculator: VectorCalculator
) : SoleCalculator {

    override fun solveSole(a: Matrix, b: Matrix): List<Double> {
        var ab = matrixCalculator.extend(a, b)

        ab = findInverseMatrixForLowerHalf(ab)
        ab = getReversedMatrix(ab)
        ab = findInverseMatrixForLowerHalf(ab)

        return ab.columns.last().coordinates.reversed()
    }


    private fun findInverseMatrixForLowerHalf(m: Matrix): Matrix {
        var inverse = m
        for (i in m.rows.indices) {
            inverse = inverse.setRow(
                i, vectorCalculator.multiply(
                    inverse[i], 1 / inverse[i][i]
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
        for (i in 0 until rowsCount/2) {
            result = result.swapRows(i, rowsCount - 1 - i)
        }
        for (i in 0 until rowsCount/2) {
            result = result.swapColumns(i, rowsCount - 1 - i)
        }
        return result
    }

    private fun sortInverseMatrix(m: Matrix): Matrix = Matrix(m.rows.sortedBy {
        findZerosCount(it)
    })

    private fun findZerosCount(v: Vector): Int {
        var zeros = 0
        for (i in v.coordinates) {
            if (i == 0.0) {
                zeros++
            } else {
                return zeros
            }
        }
        return zeros
    }
}