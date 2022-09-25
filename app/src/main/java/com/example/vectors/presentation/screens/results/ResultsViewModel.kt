package com.example.vectors.presentation.screens.results

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.example.vectorcalculations_android.vectorcalculations.vector.Vector
import com.example.vectorcalculations_android.vectorcalculations.vector.VectorCalculator
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class ResultsViewModel @Inject constructor(private val calculator: com.example.vectorcalculations_android.vectorcalculations.vector.VectorCalculator) : ViewModel() {
    private val _state = mutableStateOf<ResultsState>(ResultsState.Init)
    val state: State<ResultsState> = _state

    fun calculate(v1: com.example.vectorcalculations_android.vectorcalculations.vector.Vector, v2: com.example.vectorcalculations_android.vectorcalculations.vector.Vector) {
        calculator.apply {
            _state.value = ResultsState.FewVectors(
                sum = sum(v1, v2),
                subtract = subtract(v1, v2),
                multiplyScalar = multiplyScalar(v1, v2),
                cos = cos(v1, v2),
                collinearity = vectorsCollinearity(v1, v2),
                equal = vectorsEquals(v1, v2),
                orthogonal = vectorsOrthogonal(v1, v2),
                projectionScalar = projectionScalar(v1, v2),
                projectionVector = projectionVector(v1, v2)
            )
        }
    }
}