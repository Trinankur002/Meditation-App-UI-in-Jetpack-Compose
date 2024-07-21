package com.example.uitest

import android.annotation.SuppressLint
import android.app.Activity
import android.os.Bundle
import android.view.WindowInsetsController
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalView
import androidx.compose.ui.unit.dp
import androidx.core.view.WindowCompat
import androidx.core.view.WindowInsetsCompat
import com.example.uitest.datas.BottomMenuContentList
import com.example.uitest.ui.BottomMenu
import com.example.uitest.ui.HomeScreen
import com.example.uitest.ui.theme.UiTestTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            UiTestTheme {
                MainScreen()
            }
        }
    }
}


//@Composable
//fun MainScreen() {
//    Box(){
//        HomeScreen()
//        BottomMenu(
//            items = BottomMenuContentList,
//            modifier = Modifier.align(Alignment.BottomCenter)
//        )
//    }
//}

@SuppressLint("WrongConstant")
@Composable
fun MainScreen() {
    val context = LocalContext.current
    val view = LocalView.current

    SideEffect {
        val window = (context as Activity).window
        val windowInsetsController = WindowCompat.getInsetsController(window, view)

        windowInsetsController?.let {
            it.hide(WindowInsetsCompat.Type.systemBars())
            it.systemBarsBehavior = WindowInsetsController.BEHAVIOR_SHOW_TRANSIENT_BARS_BY_SWIPE
        }
    }

    Box(modifier = Modifier.fillMaxSize()) {
        HomeScreen()
        BottomMenu(items = BottomMenuContentList, modifier = Modifier.align(Alignment.BottomCenter))
    }
}
