package com.example.uitest.datas

import androidx.compose.runtime.Composable
import androidx.compose.runtime.Recomposer
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.derivedStateOf
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.uitest.ui.HomeScreen
import com.example.uitest.ui.MeditationScreen
import com.example.uitest.ui.MusicScreen
import com.example.uitest.ui.ProfileScreen
import com.example.uitest.ui.SleepScreen

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
