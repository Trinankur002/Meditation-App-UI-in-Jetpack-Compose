package com.example.takecare.datas

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.takecare.ui.HomeScreen
import com.example.takecare.ui.MeditationScreen
import com.example.takecare.ui.MusicScreen
import com.example.takecare.ui.ProfileScreen
import com.example.takecare.ui.SleepScreen

@Composable
fun NavigationHost(navController: NavHostController) {
    NavHost(navController = navController, startDestination = "home") {
        composable("home") { HomeScreen() }
        composable("meditate") { MeditationScreen() }
        composable("sleep") { SleepScreen() }
        composable("music") { MusicScreen() }
        composable("profile") { ProfileScreen() }
    }
}
