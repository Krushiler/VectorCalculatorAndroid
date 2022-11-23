package com.example.vectors.presentation.screens.matrix.home

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MatrixHomeViewModel @Inject constructor() : ViewModel() {
    private val _state = mutableStateOf<MatrixHomeState>(MatrixHomeState.Init)
    val stateMatrix: State<MatrixHomeState> = _state

    fun backToMenu() {
        _state.value = MatrixHomeState.Init
    }

    fun dimensEntered(dimens: Int) {
        _state.value = MatrixHomeState.VectorInput(dimens)
    }
}