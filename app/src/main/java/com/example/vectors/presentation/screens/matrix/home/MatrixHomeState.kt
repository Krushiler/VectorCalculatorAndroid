package com.example.vectors.presentation.screens.matrix.home

sealed class MatrixHomeState {
    object Init: MatrixHomeState()
    data class VectorInput(val dimens: Int): MatrixHomeState()
}