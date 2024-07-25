package com.example.takecare.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.takecare.ui.theme.Cyan400

@Composable
fun ProfileScreen() {
    Text(
        text = "Profile is good",
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

@Preview
@Composable
private fun ProfileScreenPreview() {
    MeditationScreen()
}