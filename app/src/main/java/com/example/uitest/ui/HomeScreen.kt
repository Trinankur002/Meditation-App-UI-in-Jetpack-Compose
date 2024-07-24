package com.example.uitest.ui
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.surfaceColorAtElevation
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.uitest.R
import com.example.uitest.datas.Feature
import com.example.uitest.datas.FeaturesItemList
import com.example.uitest.datas.standardQuadFromTo

@Preview
@Composable
fun HomeScreen() {
    Column(
        modifier = Modifier.fillMaxSize(),
    ) {
        Box(modifier = Modifier
            .background(Color(0xFF000B4B))
            .fillMaxSize()
        ){
            Column() {
                Greeting()
                ChipSection(chips = listOf("Sweet Sleep", "Insomnia", "Depression"))
                CurrentMeditation()
                FeaturedSection(FeaturesItemList)
            }
        }
    }
}

@Composable
fun Greeting(name : String = "Trinankur") {
    Row (
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .fillMaxWidth()
            .padding(15.dp)
    ){
        Column(
            verticalArrangement = Arrangement.Center
        ) {
            Text(
                text = "Good Morning, $name",
                style = MaterialTheme.typography.headlineSmall,
                color = Color.White,
            )
            Text(
                text = "We wish you have a good day!",
                style = MaterialTheme.typography.bodyLarge,
                color = Color.White,
            )
        }
        Icon(
            painter = painterResource(id = R.drawable.baseline_search_24),
            contentDescription = "Search",
            tint = Color.White,
            modifier = Modifier.size(35.dp)
        )
    }
}


@Composable
fun ChipSection(chips: List<String>) {
    var selectedChipIndex by remember {
        mutableStateOf(0)
    }
    LazyRow {
        items(chips.size){
            Box(
                contentAlignment = Alignment.Center,
                modifier = Modifier
                    .padding(
                        start = 15.dp,
                        top = 15.dp,
                        bottom = 15.dp
                    )
                    .clickable {
                        selectedChipIndex = it
                    }
                    .clip(RoundedCornerShape(10.dp))
                    .background(
                        if (selectedChipIndex == it) Color(0xFF5E76F7)
                        else Color(0xFF313F8D)
                    )
                    .padding(10.dp)
            ){
                Text(
                    text = chips[it],
                    color = Color(0xE4EFF0F3),
                    style = MaterialTheme.typography.bodyLarge
                )
            }
        }
    }
}


@Composable
fun CurrentMeditation(color: Color = Color(0xD2DF2161)) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween,
        modifier = Modifier
            .padding(15.dp)
            .clip(RoundedCornerShape(10.dp))
            .fillMaxWidth()
            .background(color)
            .padding(vertical = 20.dp, horizontal = 15.dp)
    ) {
        Column(
            verticalArrangement = Arrangement.Center,
        ) {
            Text(
                text = "Daily Thought",
                style = MaterialTheme.typography.headlineSmall,
                color = Color.White,
            )
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween,
            ){
                Text(
                    text = "Meditation",
                    style = MaterialTheme.typography.bodyLarge,
                    color = Color.White,
                )
                Text(
                    text = "  •  ",
                    style = MaterialTheme.typography.bodyLarge,
                    color = Color.White,
                )
                Text(
                    text = "3-10 min",
                    style = MaterialTheme.typography.bodyLarge,
                    color = Color.White,
                )
            }
        }
        var isPlaying by remember { mutableStateOf(false)}
        Box(
            modifier = Modifier
                .size(40.dp)
                .clip(CircleShape)
                .background(color = Color(0xEE5C73F1))
                .clickable {
                    isPlaying = !isPlaying
                }
        ){
            Icon(
                if (isPlaying){ painterResource(id = R.drawable.pause) }
                else { painterResource(id =R.drawable.play_arrow ) },
                contentDescription = "Play/Pause",
                tint = Color(0xD2F5F5F5),
                modifier = Modifier.size(40.dp)
            )
        }
    }
}

@Composable
fun FeaturedSection(features: List<Feature>) {
    Column (modifier = Modifier.fillMaxWidth()) {
        Text(
            text = "Featured",
            style = MaterialTheme.typography.headlineSmall,
            color = Color(0xB0F5F5F5),
            modifier = Modifier.padding(15.dp)
        )
        LazyVerticalGrid(
            columns = GridCells.Fixed(2),
            contentPadding = PaddingValues(start = 7.5.dp, end = 7.5.dp, bottom = 100.dp),
            modifier = Modifier.fillMaxHeight()
        ) {
            items(features.size){
                FeaturedItems(features= features[it])
            }
        }
    }
}

@Composable
fun FeaturedItems(features: Feature) {
    BoxWithConstraints(
        modifier = Modifier
            .padding(7.5.dp)
            .aspectRatio(1f)
            .clip(RoundedCornerShape(10.dp))
            .background(features.darkColor)
    ) {
        val width = constraints.maxWidth
        val height = constraints.maxHeight

        val mediumColorPoint1 = Offset(0f,height*0.3f)
        val mediumColorPoint2 = Offset(width*0.1f,height*0.35f)
        val mediumColorPoint3 = Offset(width*0.4f,height*0.05f)
        val mediumColorPoint4 = Offset(width*0.75f,height*0.7f)
        val mediumColorPoint5 = Offset(width*1.4f,-height.toFloat())

        val mediumColoredPath = Path().apply {
            moveTo(mediumColorPoint1.x,mediumColorPoint1.y)
            standardQuadFromTo(mediumColorPoint1,mediumColorPoint2)
            standardQuadFromTo(mediumColorPoint2,mediumColorPoint3)
            standardQuadFromTo(mediumColorPoint3,mediumColorPoint4)
            standardQuadFromTo(mediumColorPoint4,mediumColorPoint5)
            lineTo(width.toFloat()+100f,height.toFloat()+100f)
            lineTo(-100f,height.toFloat()+100f)
            close()
        }
        val lightPoint1 = Offset(0f, height * 0.35f)
        val lightPoint2 = Offset(width * 0.1f, height * 0.4f)
        val lightPoint3 = Offset(width * 0.3f, height * 0.35f)
        val lightPoint4 = Offset(width * 0.65f, height.toFloat())
        val lightPoint5 = Offset(width * 1.4f, -height.toFloat() / 3f)

        val lightColoredPath = Path().apply {
            moveTo(lightPoint1.x, lightPoint1.y)
            standardQuadFromTo(lightPoint1, lightPoint2)
            standardQuadFromTo(lightPoint2, lightPoint3)
            standardQuadFromTo(lightPoint3, lightPoint4)
            standardQuadFromTo(lightPoint4, lightPoint5)
            lineTo(width.toFloat() + 100f, height.toFloat() + 100f)
            lineTo(-100f, height.toFloat() + 100f)
            close()
        }

        Canvas(modifier = Modifier.fillMaxSize()){
            drawPath(
                path = mediumColoredPath,
                color = features.mediumColor
            )
            drawPath(
                path = lightColoredPath,
                color = features.lightColor
            )
        }
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(15.dp)
        ){
            Text(
                text = features.title,
                style = MaterialTheme.typography.headlineSmall,
                color = MaterialTheme.colorScheme.surfaceColorAtElevation(5.dp),
                lineHeight = 26.sp,
                modifier = Modifier
                    .background(
                        color = features.darkColor,
                        shape = RoundedCornerShape(10.dp)
                    )
                    .align(Alignment.TopStart)
                    .padding(5.dp),
            )
            Icon(
                painter = painterResource(id = features.iconid),
                contentDescription = features.title,
                tint = Color(0xF5FFFFFF),
                modifier = Modifier
                    .align(Alignment.BottomStart)
                    .padding(vertical = 10.dp, horizontal = 6.dp),
            )

            Button(
                onClick = {
                    // Handle click
                },
                modifier = Modifier
                    .align(Alignment.BottomEnd)
                    .padding(horizontal = 6.dp),
                shape = RoundedCornerShape(10.dp) ,
                colors = ButtonDefaults.buttonColors(containerColor = Color(0xEE5C73F1))
            ) {
                Text(
                    text = "Start",
                    color = Color(0xF5FFFFFF),
                    fontSize = 14.sp,
                    fontWeight = MaterialTheme.typography.headlineSmall.fontWeight
                )
            }
        }
    }
}


