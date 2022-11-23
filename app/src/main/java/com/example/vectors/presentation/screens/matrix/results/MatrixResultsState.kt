package com.example.vectors.presentation.screens.matrix.results

import com.example.vectorcalculations_android.vectorcalculations.matrix.Matrix

sealed class MatrixResultsState{
    object Init: MatrixResultsState()
    data class FewVectors(
        val sum: Matrix,
        val subtract: Matrix,
        val multiplyScalar: Matrix
    ): MatrixResultsState()
}