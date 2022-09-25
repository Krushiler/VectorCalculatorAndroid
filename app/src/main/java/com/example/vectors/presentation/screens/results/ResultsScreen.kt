package com.example.vectors.presentation.screens.results

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
import com.example.vectorcalculations_android.vectorcalculations.vector.Vector
import com.example.vectorcalculations_android.vectorcalculations.vector.VectorsCollinearity
import com.example.vectors.navigation.NavigationDestination
import com.example.vectors.util.parcelable
import com.example.vectorcalculations_android.vectorcalculations.util.roundTo
import com.example.vectorcalculations_android.vectorcalculations.util.roundedTo
import com.google.accompanist.insets.statusBarsPadding
import com.google.gson.Gson
import kotlinx.parcelize.Parcelize

@Parcelize
data class ResultsScreenData(
    val v1: com.example.vectorcalculations_android.vectorcalculations.vector.Vector,
    val v2: com.example.vectorcalculations_android.vectorcalculations.vector.Vector
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
                .statusBarsPadding()
                .verticalScroll(
                    rememberScrollState()
                )
                .padding(all = 16.dp)
        ) {
            val state = screenState as ResultsState.FewVectors
            Text(text = data.v1.toString())
            Text(text = "Длина: ${data.v1.length.roundTo(3)}")
            Spacer(modifier = Modifier.height(8.dp))
            Text(text = data.v2.toString())
            Text(text = "Длина: ${data.v2.length.roundTo(3)}")
            Spacer(modifier = Modifier.height(16.dp))

            Result("Сумма", state.sum.roundedTo(3).toString())
            Result("Разность", state.subtract.roundedTo(3).toString())

            Result(
                "Скалярное произведение", "${state.multiplyScalar.roundTo(3)}"
            )

            Result("Косинус угла", state.cos.roundTo(3).toString())

            if (state.collinearity == com.example.vectorcalculations_android.vectorcalculations.vector.VectorsCollinearity.NOT_COLLINEAR) {
                Result("Коллинеарность векторов", "Не коллинеарны")
            } else {
                Result("Коллинеарность векторов", "Коллинеанры")
                val directionString = when (state.collinearity) {
                    com.example.vectorcalculations_android.vectorcalculations.vector.VectorsCollinearity.CO_DIRECTIONAL -> "Сонаправлены"
                    com.example.vectorcalculations_android.vectorcalculations.vector.VectorsCollinearity.OPPOSITE -> "Противоположны"
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
                "Скалярная:", "${state.projectionScalar.roundTo(3)}",
                "Векторная:", "${state.projectionScalar.roundTo(3)}"
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