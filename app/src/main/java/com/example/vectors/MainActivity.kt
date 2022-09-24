package com.example.vectors

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Surface
import androidx.compose.ui.Modifier
import com.example.vectors.navigation.NavigationComponent
import com.example.vectors.ui.theme.VectorAppTheme
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            VectorAppTheme(darkTheme = false) {
                Surface(modifier = Modifier.fillMaxSize()) {
                    NavigationComponent()
                }
            }
        }
    }
}