package com.example.vectors.presentation.screens.home

import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.vectors.domain.vector.Vector
import com.example.vectors.presentation.screens.results.ResultsScreenData
import com.example.vectors.presentation.screens.results.navigateToResults
import com.example.vectors.presentation.views.CordsInput

@Composable
fun HomeScreen(viewModel: HomeViewModel, navController: NavController) {

    val screenState by viewModel.state

    Column(modifier = Modifier.fillMaxSize(), verticalArrangement = Arrangement.Center, horizontalAlignment = Alignment.CenterHorizontally) {
        when(screenState) {
            HomeState.Init -> {
                val dimens = remember {
                    mutableStateOf("")
                }
                Text(text = "Введите размерность")
                Spacer(modifier = Modifier.height(16.dp))
                TextField(value = dimens.value, onValueChange = {
                    dimens.value = it
                })
                Spacer(modifier = Modifier.height(16.dp))
                Button(onClick = {
                    viewModel.dimensEntered(dimens.value.toInt())
                }) {
                    Text(text = "Ввод")
                }
            }
            is HomeState.VectorInput -> {
                val state = screenState as HomeState.VectorInput
                val firstCord = remember {
                    mutableStateOf(List(size = state.dimens, init = { 0.0 }))
                }
                val secondCord = remember {
                    mutableStateOf(List(size = state.dimens, init = { 0.0 }))
                }

                CordsInput(dimens = state.dimens, onChanged = {
                    firstCord.value = it
                })
                Spacer(modifier = Modifier.height(16.dp))
                CordsInput(dimens = state.dimens, onChanged = {
                    secondCord.value = it
                })
                Spacer(modifier = Modifier.height(16.dp))
                Button(onClick = {
                    navigateToResults(navController, ResultsScreenData(
                        v1 = Vector(firstCord.value.toList()),
                        v2 = Vector(secondCord.value.toList())
                    ))
                }) {
                    Text(text = "Ввод")
                }
            }
        }
    }
}