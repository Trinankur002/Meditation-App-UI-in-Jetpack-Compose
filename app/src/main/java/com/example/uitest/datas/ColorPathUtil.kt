package com.example.uitest.datas
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.geometry.Offset
import kotlin.math.abs

//fun Path.standardQuadFromTo(from: Offset, to: Offset) {
//    quadraticBezierTo(
//        from.x,
//        from.y,
//        abs(from.x + to.x) / 2f,
//        abs(from.y + to.y) / 2f
//    )
//}

fun Path.standardQuadFromTo(from: Offset, to: Offset) {
    val controlPoint = Offset((from.x + to.x) / 2f, (from.y + to.y) / 2f)
    quadraticBezierTo(controlPoint.x, controlPoint.y, to.x, to.y)
}
