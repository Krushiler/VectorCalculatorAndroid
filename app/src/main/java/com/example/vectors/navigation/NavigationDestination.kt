package com.example.vectors.navigation

sealed class NavigationDestination(val name: String) {
    object HomeScreen: NavigationDestination("Home")
    object ResultsScreen: NavigationDestination("Results")
}