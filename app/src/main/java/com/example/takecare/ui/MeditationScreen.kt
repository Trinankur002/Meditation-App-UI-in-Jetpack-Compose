package com.example.takecare.ui

import android.annotation.SuppressLint
import android.content.Context
import android.media.MediaPlayer
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.takecare.R
import com.example.takecare.datas.TimeOfMeditate
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@Preview
@Composable
fun MeditationScreen() {
    Column{
        Box{
            Column(
                modifier = Modifier
                    .align(alignment = Alignment.Center)
                    .background(Color(0xFF000B4B))
                    .fillMaxSize()
                    .verticalScroll(state = rememberScrollState()),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.SpaceEvenly,
            ) { //column space
                Image(
                    painter = painterResource(R.drawable.meditation_image),
                    contentDescription = "Meditation Image",
                    contentScale = ContentScale.Inside,
                    modifier = Modifier
                        .fillMaxWidth(0.9f)
                        .padding(top = 40.dp)
                )
                CountdownTimer(TimeOfMeditate, R.raw.simplenotification2)
            }
        }
    }
}

@SuppressLint("DefaultLocale")
@Composable
fun CountdownTimer(totalTimeInSeconds: Int, soundResId: Int) {
    var timeInSeconds by remember { mutableIntStateOf(totalTimeInSeconds) }
    val minutes = timeInSeconds / 60
//    val seconds = timeInSeconds % 60

    var isPlaying by remember { mutableStateOf(false) }
    var isReadyToReset by remember { mutableStateOf(false) }
    val coroutineScope = rememberCoroutineScope()
    val context = LocalContext.current
    var playMySound = MediaPlayer.create(context, soundResId)

    fun startMeditate() {
        coroutineScope.launch {
            while (timeInSeconds > 0 && isPlaying) {
                timeInSeconds--
                delay(1000L)
                if (timeInSeconds == 0) {
                    playMySound.start()
                    delay(3000L)
                    playMySound?.release()
                    playMySound = null
                    isPlaying = false
                }
            }
        }
    }
    Column(
        modifier = Modifier
            .padding(16.dp)
            .fillMaxHeight(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceEvenly,
    ) { //column space
        Text(
            text = String.format("%02d min", minutes),
            fontSize = 50.sp,
            color = Color.White,
            textAlign = TextAlign.Center
        )
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.SpaceEvenly,
        ) { //column space
            if (!isPlaying && timeInSeconds >= 0){
                Text(
                    text = "Start Your Meditation Session",
                    fontSize = 25.sp,
                    color = Color.White,
                    textAlign = TextAlign.Center
                )
            }
            Spacer(modifier = Modifier.height(18.dp))
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically,
            ) { // Row space
                Box(

                    modifier = Modifier
                        .size(40.dp)
                        .clip(CircleShape)
                        .background(color = Color(0xEE5C73F1))
                        .clickable {
                            if (timeInSeconds > 300) { timeInSeconds -= 300 ; return@clickable }
                            if (timeInSeconds == 300 || timeInSeconds in 1..299) { timeInSeconds -= 60 ; return@clickable }
                            if (timeInSeconds in 0..60) { timeInSeconds = 0 ; !isPlaying ; return@clickable  }
//                            if (timeInSeconds <=0 ){ timeInSeconds = 0 }
                        }
                ) {
                    Icon(
                        painter = painterResource(R.drawable.minus),
                        contentDescription = "minus",
                        tint = Color(0xD2F5F5F5),
                        modifier = Modifier.fillMaxSize()
                    )
                }
                Spacer(modifier = Modifier.width(16.dp))
                //the main button
                Box(
                    modifier = Modifier
                        .size(60.dp)
                        .clip(CircleShape)
                        .background(color = Color(0xEE5C73F1))
                        .clickable {
                            if(timeInSeconds != 0){
                                isPlaying = !isPlaying
                                if (isPlaying) {
                                    startMeditate()
                                }
                                isReadyToReset = true
                            }
                        }
                ) {
                    Icon(
                        painter = if (isPlaying) painterResource(id = R.drawable.pause)
                        else painterResource(id = R.drawable.play_arrow),
                        contentDescription = "Play/Pause",
                        tint = Color(0xD2F5F5F5),
                        modifier = Modifier.fillMaxSize()
                    )
                }
                Spacer(modifier = Modifier.width(16.dp))
                Box(
                    modifier = Modifier
                        .size(40.dp)
                        .clip(CircleShape)
                        .background(color = Color(0xEE5C73F1))
                        .clickable {
                            if (timeInSeconds < 300){timeInSeconds = 300}
                            else timeInSeconds += 300
                        }
                ) {
                    Icon(
                        painter = painterResource(R.drawable.plus),
                        contentDescription = "plus",
                        tint = Color(0xD2F5F5F5),
                        modifier = Modifier.fillMaxSize()
                    )
                }
            }
            Spacer(modifier = Modifier.height(18.dp))
            // reset button here
            if (isReadyToReset){
                Box(
                    modifier = Modifier
                        .size(40.dp)
                        .clip(CircleShape)
                        .background(color = Color(0xEE5C73F1))
                        .clickable {
                            timeInSeconds = totalTimeInSeconds
                            isPlaying = false
                            isReadyToReset = false
                        }
                ) {
                    Icon(
                        painter = painterResource(R.drawable.reset),
                        contentDescription = "Reset",
                        tint = Color(0xD2F5F5F5),
                        modifier = Modifier.fillMaxSize()
                    )
                }
            }
        }

    }
}

private fun playMySound(soundResId: Int, context: Context) {
    val mediaPlayer = MediaPlayer.create(context, soundResId)
    mediaPlayer.start()
}