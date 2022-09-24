package com.example.vectors.presentation.screens.home

sealed class HomeState {
    object Init: HomeState()
    data class VectorInput(val dimens: Int): HomeState()
}