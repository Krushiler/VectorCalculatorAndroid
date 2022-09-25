package com.example.vectors.presentation.views

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import com.google.accompanist.flowlayout.FlowCrossAxisAlignment
import com.google.accompanist.flowlayout.FlowRow
import com.google.accompanist.flowlayout.MainAxisAlignment

@Composable
fun CordsInput(dimens: Int, onChanged: (List<Double>?) -> Unit) {

    val cords = remember {
        mutableStateOf(List(size = dimens, init = { "0" }))
    }

    FlowRow(
        modifier = Modifier.fillMaxWidth(),
        mainAxisAlignment = MainAxisAlignment.Center,
        crossAxisAlignment = FlowCrossAxisAlignment.Center,
        crossAxisSpacing = 8.dp
    ) {
        repeat(dimens) { index ->
            TextField(value = cords.value[index],
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                modifier = Modifier
                    .width(60.dp)
                    .onFocusChanged { focusState ->
                        if (focusState.isFocused) {
                            cords.value = cords.value
                                .toMutableList()
                                .apply {
                                    this[index] = ""
                                }
                        }
                    }, onValueChange = { text ->
                    cords.value = cords.value.toMutableList().apply {
                        this[index] = text
                    }

                    for (el in cords.value) {
                        if (el.toDoubleOrNull() == null) {
                            onChanged.invoke(null)
                            return@TextField
                        }
                    }
                    onChanged.invoke(cords.value.map {
                        it.toDouble()
                    })
                }
            )
            if (index < dimens)
                Spacer(modifier = Modifier.width(8.dp))
        }
    }
}