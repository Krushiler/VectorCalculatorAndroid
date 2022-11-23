package com.example.vectors.presentation.screens.matrix.results

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.example.vectorcalculations_android.vectorcalculations.matrix.Matrix
import com.example.vectorcalculations_android.vectorcalculations.matrix.MatrixCalculator
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MatrixResultsViewModel @Inject constructor(private val calculator: MatrixCalculator) : ViewModel() {
    private val _state = mutableStateOf<MatrixResultsState>(MatrixResultsState.Init)
    val stateMatrix: State<MatrixResultsState> = _state

    fun calculate(m1: Matrix, m2: Matrix) {
        calculator.apply {
            _state.value = MatrixResultsState.FewVectors(
                sum = sum(m1, m2),
                subtract = subtract(m1, m2),
                multiplyScalar = multiplyScalar(m1, m2)
            )
        }
    }
}