package com.example.vectors.presentation.screens.results

import android.net.Uri
import android.os.Bundle
import android.os.Parcelable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.NavType
import com.example.vectors.domain.vector.Vector
import com.example.vectors.domain.vector.VectorsCollinearity
import com.example.vectors.navigation.NavigationDestination
import com.example.vectors.util.parcelable
import com.example.vectors.util.roundTo
import com.google.gson.Gson
import kotlinx.parcelize.Parcelize

@Parcelize
data class ResultsScreenData(
    val v1: Vector,
    val v2: Vector
) : Parcelable {
    companion object NavigationType : NavType<ResultsScreenData>(isNullableAllowed = false) {
        override fun get(bundle: Bundle, key: String): ResultsScreenData? {
            return bundle.parcelable(key)
        }

        override fun parseValue(value: String): ResultsScreenData {
            return Gson().fromJson(value, ResultsScreenData::class.java)
        }

        override fun put(bundle: Bundle, key: String, value: ResultsScreenData) {
            bundle.putParcelable(key, value)
        }
    }
}

fun navigateToResults(navController: NavController, data: ResultsScreenData) {
    val dataJson = Uri.encode(Gson().toJson(data))
    navController.navigate("${NavigationDestination.ResultsScreen.name}/$dataJson")
}

@Composable
fun ResultsScreen(viewModel: ResultsViewModel, data: ResultsScreenData) {
    viewModel.calculate(data.v1, data.v2)

    val screenState by viewModel.state
    when (screenState) {
        ResultsState.Init -> {}
        is ResultsState.FewVectors -> Column(
            Modifier
                .fillMaxSize()
                .verticalScroll(
                    rememberScrollState()
                )
        ) {
            val state = screenState as ResultsState.FewVectors
            Text(text = "${data.v1} Длина: ${data.v1.length.roundTo(3)}")
            Spacer(modifier = Modifier.height(8.dp))
            Text(text = "${data.v2} Длина: ${data.v2.length.roundTo(3)}")
            Spacer(modifier = Modifier.height(16.dp))

            Result("Сумма", state.sum.toString())
            Result("Разность", state.subtract.toString())

            Result(
                "Произведение", "Скалярное: ${state.multiplyScalar.roundTo(3)}",
                "Векторное: ${state.multiplyVector}"
            )

            Result("Косинус угла", state.cos.roundTo(3).toString())

            if (state.collinearity == VectorsCollinearity.NOT_COLLINEAR) {
                Result("Коллинеарность векторов", "Не коллинеарны")
            } else {
                Result("Коллинеарность векторов", "Коллинеанры")
                val directionString = when (state.collinearity) {
                    VectorsCollinearity.CO_DIRECTIONAL -> "Сонаправлены"
                    VectorsCollinearity.OPPOSITE -> "Противоположны"
                    else -> ""
                }
                Result("Направленность векторов: ", directionString)
            }

            val equalString = if (state.equal) "Равны" else "Не равны"
            Result("Равенство векторов", equalString)

            val orthogonalString = if (state.orthogonal) "Ортогональны" else "Не ортогональны"
            Result("Ортогональность векторов", orthogonalString)

            Result(
                "Проекция вектора 1 на вектор 2",
                "Скалярная: ${state.projectionScalar.roundTo(3)}",
                "Векторная: ${state.projectionScalar}"
            )
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