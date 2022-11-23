package com.example.vectors.presentation.screens.matrix.home

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.vectors.presentation.screens.vectors.results.ResultsScreenData
import com.example.vectors.presentation.screens.vectors.results.navigateToResults
import com.example.vectors.presentation.views.CordsInput
import com.google.accompanist.insets.ProvideWindowInsets
import com.google.accompanist.insets.navigationBarsWithImePadding
import com.google.accompanist.insets.statusBarsPadding

@Composable
fun MatrixHomeScreen(viewModelMatrix: MatrixHomeViewModel, navController: NavController) {

    val screenState by viewModelMatrix.stateMatrix

    ProvideWindowInsets(windowInsetsAnimationsEnabled = true) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .statusBarsPadding()
                .navigationBarsWithImePadding()
                .verticalScroll(rememberScrollState()),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            when (screenState) {
                MatrixHomeState.Init -> {
                    val dimens = remember {
                        mutableStateOf("")
                    }
                    Text(text = "Введите размерность")
                    Spacer(modifier = Modifier.height(16.dp))
                    TextField(
                        modifier = Modifier.width(60.dp),
                        value = dimens.value, onValueChange = {
                            dimens.value = it
                        },
                        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)

                    )
                    Spacer(modifier = Modifier.height(16.dp))
                    Button(onClick = {
                        viewModelMatrix.dimensEntered(dimens.value.toInt())
                    }) {
                        Text(text = "Ввод")
                    }
                }
                is MatrixHomeState.VectorInput -> {

                    BackHandler {
                        viewModelMatrix.backToMenu()
                    }

                    val state = screenState as MatrixHomeState.VectorInput
                    val firstCord = remember {
                        mutableStateOf(List(size = state.dimens, init = { 0.0 }))
                    }
                    val secondCord = remember {
                        mutableStateOf(List(size = state.dimens, init = { 0.0 }))
                    }

                    val firstCorrect = remember {
                        mutableStateOf(true)
                    }
                    Text(text = "Вектор 1")
                    Spacer(modifier = Modifier.height(16.dp))
                    CordsInput(dimens = state.dimens, onChanged = {
                        if (it != null) {
                            firstCord.value = it
                            firstCorrect.value = true
                        } else {
                            firstCorrect.value = false
                        }
                    })
                    Spacer(modifier = Modifier.height(32.dp))

                    Text(text = "Вектор 2")
                    Spacer(modifier = Modifier.height(16.dp))

                    val secondCorrect = remember {
                        mutableStateOf(true)
                    }

                    CordsInput(dimens = state.dimens, onChanged = {
                        if (it != null) {
                            secondCord.value = it
                            secondCorrect.value = true
                        } else {
                            secondCorrect.value = false
                        }
                    })
                    Spacer(modifier = Modifier.height(16.dp))
                    Button(
                        onClick = {
                            navigateToResults(
                                navController, ResultsScreenData(
                                    v1 = com.example.vectorcalculations_android.vectorcalculations.vector.Vector(
                                        firstCord.value.toList()
                                    ),
                                    v2 = com.example.vectorcalculations_android.vectorcalculations.vector.Vector(
                                        secondCord.value.toList()
                                    )
                                )
                            )
                        }, enabled = firstCorrect.value && secondCorrect.value
                    ) {
                        Text(text = "Ввод")
                    }
                }
            }
        }
    }
}