package com.example.vectors.presentation.screens.vectors.home

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor() : ViewModel() {
    private val _state = mutableStateOf<HomeState>(HomeState.Init)
    val state: State<HomeState> = _state

    fun backToMenu() {
        _state.value = HomeState.Init
    }

    fun dimensEntered(dimens: Int) {
        _state.value = HomeState.VectorInput(dimens)
    }
}