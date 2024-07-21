package com.example.uitest.datas

import androidx.annotation.DrawableRes
import com.example.uitest.R

data class BottomMenuContent(
    val title: String,
    @DrawableRes val iconId: Int
)

val BottomMenuContentList = listOf(
    BottomMenuContent("Home", R.drawable.home),
    BottomMenuContent("Meditate", R.drawable.meditation),
    BottomMenuContent("Sleep", R.drawable.sleep),
    BottomMenuContent("Music", R.drawable.music),
    BottomMenuContent("Profile", R.drawable.profile),
)