package com.example.vectors.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.vectors.presentation.screens.home.HomeScreen
import com.example.vectors.presentation.screens.home.HomeViewModel
import com.example.vectors.presentation.screens.results.ResultsScreen
import com.example.vectors.presentation.screens.results.ResultsScreenData
import com.example.vectors.presentation.screens.results.ResultsViewModel
import com.example.vectors.util.parcelable

@Composable
fun NavigationComponent() {
    val navController = rememberNavController()

    LaunchedEffect(NavigationDestination.HomeScreen.name, block = {
        if (navController.currentDestination?.route != NavigationDestination.HomeScreen.name) {
            navController.navigate(NavigationDestination.HomeScreen.name)
        }
    })

    NavHost(navController, startDestination = NavigationDestination.HomeScreen.name) {
        composable(NavigationDestination.HomeScreen.name) {
            val viewModel: HomeViewModel = hiltViewModel()
            HomeScreen(viewModel, navController)
        }
        composable(route = "${NavigationDestination.ResultsScreen.name}/{data}", arguments = listOf(
            navArgument("data") {
                type = ResultsScreenData.NavigationType
            }
        )) { entry ->
            val data: ResultsScreenData? = entry.arguments?.parcelable("data")
            val viewModel: ResultsViewModel = hiltViewModel()
            if (data != null) {
                ResultsScreen(viewModel, data)
            }
        }
    }
}