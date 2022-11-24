package com.example.vectorcalculations_android.vectorcalculations.sole

import com.example.vectorcalculations_android.vectorcalculations.matrix.Matrix
import com.example.vectorcalculations_android.vectorcalculations.matrix.MatrixCalculator
import com.example.vectorcalculations_android.vectorcalculations.vector.Vector
import com.example.vectorcalculations_android.vectorcalculations.vector.VectorCalculator

interface ISoleCalculator {
    fun solveSole(a: Matrix, b: Matrix): List<Double>
}