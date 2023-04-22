package com.aqude.menstrualcyclecalculator.routes

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.aqude.menstrualcyclecalculator.screens.CalculatorBloodScreen
import com.aqude.menstrualcyclecalculator.screens.HomeScreen
import com.aqude.menstrualcyclecalculator.screens.InfoScreen
import com.aqude.menstrualcyclecalculator.screens.SettingsScreen

@Composable
fun BottomNavGraph(navController: NavHostController) {
    NavHost(
        navController = navController,
        startDestination = BottomBarScreen.Home.route
    ) {
        composable(route = BottomBarScreen.Home.route) {
            HomeScreen()
        }
        composable(route = BottomBarScreen.Info.route) {
            InfoScreen()
        }
        composable(route = BottomBarScreen.Settings.route) {
            SettingsScreen()
        }
        composable(route = BottomBarScreen.Blood.route) {
            CalculatorBloodScreen()
        }
    }
}
