package com.example.vectorcalculations_android.vectorcalculations.sole

import com.example.vectorcalculations_android.vectorcalculations.matrix.Matrix
import com.example.vectorcalculations_android.vectorcalculations.matrix.MatrixCalculator
import com.example.vectorcalculations_android.vectorcalculations.vector.VectorCalculator

class InverseMatrixSoleCalculator(
    private val matrixCalculator: MatrixCalculator,
    private val vectorCalculator: VectorCalculator
) : ISoleCalculator {
    override fun solveSole(a: Matrix, b: Matrix): List<Double> {
        val inverse = matrixCalculator.inverse(a)
        return matrixCalculator.multiplyScalar(inverse, b).columns.last().coordinates
    }
}