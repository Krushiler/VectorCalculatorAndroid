package com.example.vectors.presentation.views

import androidx.compose.foundation.layout.*
import androidx.compose.material.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.core.text.isDigitsOnly

@Composable
fun CordsInput(dimens: Int, onChanged: (List<Double>) -> Unit) {

    val cords = remember {
        mutableStateOf(List(size = dimens, init = { "0" }))
    }

    Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.Center) {
        repeat(dimens) { index ->
            TextField(value = cords.value[index], onValueChange = { text ->
                cords.value = cords.value.toMutableList().apply {
                    this[index] = text
                }

                for (el in cords.value) {
                    if (!el.isDigitsOnly() || el.isEmpty()) return@TextField
                }
                onChanged.invoke(cords.value.map {
                    it.toDouble()
                })
            }, modifier = Modifier.width(60.dp))
            Spacer(modifier = Modifier.width(8.dp))
        }
    }
}