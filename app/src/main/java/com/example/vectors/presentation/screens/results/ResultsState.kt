package com.example.vectors.presentation.screens.results

import com.example.vectorcalculations_android.vectorcalculations.vector.Vector
import com.example.vectorcalculations_android.vectorcalculations.vector.VectorsCollinearity

sealed class ResultsState{
    object Init: ResultsState()
    data class FewVectors(
        val sum: com.example.vectorcalculations_android.vectorcalculations.vector.Vector,
        val subtract: com.example.vectorcalculations_android.vectorcalculations.vector.Vector,
        val multiplyScalar: Double,
        val cos: Double,
        val collinearity: com.example.vectorcalculations_android.vectorcalculations.vector.VectorsCollinearity,
        val equal: Boolean,
        val orthogonal: Boolean,
        val projectionScalar: Double,
        val projectionVector: com.example.vectorcalculations_android.vectorcalculations.vector.Vector
    ): ResultsState()
}