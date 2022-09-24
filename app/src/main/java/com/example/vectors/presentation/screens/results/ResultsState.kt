package com.example.vectors.presentation.screens.results

import com.example.vectors.domain.vector.Vector
import com.example.vectors.domain.vector.VectorsCollinearity

sealed class ResultsState{
    object Init: ResultsState()
    data class FewVectors(
        val sum: Vector,
        val subtract: Vector,
        val multiplyVector: Vector,
        val multiplyScalar: Double,
        val cos: Double,
        val collinearity: VectorsCollinearity,
        val equal: Boolean,
        val orthogonal: Boolean,
        val projectionScalar: Double,
        val projectionVector: Vector
    ): ResultsState()
}