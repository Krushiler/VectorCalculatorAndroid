package com.example.vectors.presentation.screens.matrix.results

import android.net.Uri
import android.os.Bundle
import android.os.Parcelable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.NavType
import com.example.vectorcalculations_android.vectorcalculations.matrix.Matrix
import com.example.vectors.navigation.NavigationDestination
import com.example.vectors.util.parcelable
import com.example.vectorcalculations_android.vectorcalculations.util.roundedTo
import com.google.accompanist.insets.statusBarsPadding
import com.google.gson.Gson
import kotlinx.parcelize.Parcelize

@Parcelize
data class MatrixResultsScreenData(
    val v1: Matrix,
    val v2: Matrix
) : Parcelable {
    companion object NavigationType : NavType<MatrixResultsScreenData>(isNullableAllowed = false) {
        override fun get(bundle: Bundle, key: String): MatrixResultsScreenData? {
            return bundle.parcelable(key)
        }

        override fun parseValue(value: String): MatrixResultsScreenData {
            return Gson().fromJson(value, MatrixResultsScreenData::class.java)
        }

        override fun put(bundle: Bundle, key: String, value: MatrixResultsScreenData) {
            bundle.putParcelable(key, value)
        }
    }
}

fun navigateToMatrixResults(navController: NavController, dataMatrix: MatrixResultsScreenData) {
    val dataJson = Uri.encode(Gson().toJson(dataMatrix))
    navController.navigate("${NavigationDestination.VectorResultsScreen.name}/$dataJson")
}

@Composable
fun MatrixResultsScreen(viewModelMatrix: MatrixResultsViewModel, dataMatrix: MatrixResultsScreenData) {
    viewModelMatrix.calculate(dataMatrix.v1, dataMatrix.v2)

    val screenState by viewModelMatrix.stateMatrix
    when (screenState) {
        MatrixResultsState.Init -> {}
        is MatrixResultsState.FewVectors -> Column(
            Modifier
                .fillMaxSize()
                .statusBarsPadding()
                .verticalScroll(
                    rememberScrollState()
                )
                .padding(all = 16.dp)
        ) {
            val state = screenState as MatrixResultsState.FewVectors
            Text(text = dataMatrix.v1.toString())
            Spacer(modifier = Modifier.height(8.dp))
            Text(text = dataMatrix.v2.toString())
            Spacer(modifier = Modifier.height(16.dp))

            Result("Сумма", state.sum.roundedTo(3).toString())
            Result("Разность", state.subtract.roundedTo(3).toString())
            Result("Произведение", state.multiplyScalar.roundedTo(3).toString())
        }
    }
}

@Composable
fun Result(name: String, vararg result: String) {
    Text(text = name)
    Spacer(modifier = Modifier.height(4.dp))
    repeat(result.size) { index ->
        Text(text = result[index])
        Spacer(modifier = Modifier.height(4.dp))
    }
    Spacer(modifier = Modifier.height(4.dp))
}