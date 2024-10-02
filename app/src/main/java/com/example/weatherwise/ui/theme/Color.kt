package com.example.weatherwise.ui.theme

import androidx.compose.material3.ColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

val Purple80 = Color(0xFFD0BCFF)
val PurpleGrey80 = Color(0xFFCCC2DC)
val Pink80 = Color(0xFFEFB8C8)

val Purple40 = Color(0xFF1B1D1F)
val Purple50 = Color(0xFF232C36)
val PurpleGrey40 = Color(0xFF21394E)
val Pink40 = Color(0xFFF3F3F3)
val Gray = Color(0xFF8E8989)

val ColorScheme.topAppBarBackgroundColor: Color
    @Composable
    get() = Purple40

val ColorScheme.topAppBarContentColor: Color
    @Composable
    get() = Pink40

val ColorScheme.screenBackgroundColor: Color
    @Composable
    get() = Purple40

val ColorScheme.screenContentColor: Color
    @Composable
    get() = Pink40

val ColorScheme.subTitleContentColor: Color
    @Composable
    get() = Gray

val ColorScheme.cardScreenBackgroundColor: Color
    @Composable
    get() = Purple50
