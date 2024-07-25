package com.example.takecare

import android.annotation.SuppressLint
import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.SystemBarStyle
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.toArgb
import androidx.navigation.compose.rememberNavController
import com.example.takecare.datas.BottomMenuContentList
import com.example.takecare.datas.NavigationHost
import com.example.takecare.ui.BottomMenu
import com.example.takecare.ui.theme.Blue900
import com.example.takecare.ui.theme.ThisAppCustomizedBackgroundColorByMe
import com.example.takecare.ui.theme.UiTestTheme

class MainActivity : ComponentActivity() {
    @RequiresApi(Build.VERSION_CODES.R)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge(
            statusBarStyle = SystemBarStyle.auto(
                ThisAppCustomizedBackgroundColorByMe.toArgb(),
                ThisAppCustomizedBackgroundColorByMe.toArgb()
            ),
            navigationBarStyle = SystemBarStyle.auto(
                Blue900.toArgb(),
                Blue900.toArgb()
            )
        )
        setContent {
            UiTestTheme {
                MainScreen()
            }
        }
    }
}


@RequiresApi(Build.VERSION_CODES.R)
@SuppressLint("WrongConstant")
@Composable
fun MainScreen() {
    val navController = rememberNavController()

    val currentBackStackEntry by navController.currentBackStackEntryFlow.collectAsState(
        initial = navController.currentBackStackEntry
    )
    val currentRoute = currentBackStackEntry?.destination?.route ?: "home"

    Scaffold(
        Modifier.fillMaxSize()
            .navigationBarsPadding(),
        bottomBar = {
            BottomMenu(
                items = BottomMenuContentList,
                currentRoute = currentRoute,
                onItemClick = { route ->
                    navController.navigate(route) {
                        popUpTo(navController.graph.startDestinationId)
                        launchSingleTop = true
                    }
                }
            )
        }
    ) { innerPadding ->
        Box(modifier = Modifier.fillMaxSize().padding(innerPadding)) {
            NavigationHost(navController = navController)
        }
    }
}
