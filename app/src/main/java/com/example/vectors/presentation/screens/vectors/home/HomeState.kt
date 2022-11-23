package com.example.vectors.presentation.screens.vectors.home

sealed class HomeState {
    object Init: HomeState()
    data class VectorInput(val dimens: Int): HomeState()
}