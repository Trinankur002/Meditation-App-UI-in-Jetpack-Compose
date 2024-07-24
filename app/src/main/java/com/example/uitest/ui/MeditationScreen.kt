package com.example.uitest.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.uitest.R
import com.example.uitest.ui.theme.Cyan400

@Preview
@Composable
fun MeditationScreen() {
    Column(
        modifier = Modifier.fillMaxSize(),
    ) {
        Box(
            modifier = Modifier
                .background(Color(0xFF000B4B))
                .fillMaxSize()
        ) {
            Column(
                modifier = Modifier
                    .align(alignment = Alignment.Center),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                Image(
                    painter = painterResource(R.drawable.meditation_image),
                    contentDescription = "Meditation Image",
                    contentScale = ContentScale.Fit,
                    modifier = Modifier
                        .fillMaxSize(0.6f)
                )
                //
                Text(
                    text = "Meditation is good",
                    textAlign = TextAlign.Center,
                    modifier = Modifier
                        .fillMaxWidth(1f)
                        .background(Color(0xFF000B4B))
                        .fillMaxHeight(1f)
                        .padding(15.dp),
                    color = Cyan400,
                    style = MaterialTheme.typography.headlineLarge,
                )
            }
        }
    }
}

