package com.example.weatherwise.ui.theme

import androidx.compose.material3.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.example.weatherwise.R

// Set of Material typography styles to start with
val Typography = Typography(
    bodyLarge = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Normal,
        fontSize = 16.sp,
        lineHeight = 24.sp,
        letterSpacing = 0.5.sp
    )
    /* Other default text styles to override
    titleLarge = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Normal,
        fontSize = 22.sp,
        lineHeight = 28.sp,
        letterSpacing = 0.sp
    ),
    labelSmall = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Medium,
        fontSize = 11.sp,
        lineHeight = 16.sp,
        letterSpacing = 0.5.sp
    )
    */
)

val chakraPetchFontFamily = FontFamily(
    Font(R.font.chakrapetch_bold, FontWeight.Bold),
    Font(R.font.chakrapetch_bold_italic, FontWeight.ExtraBold),
    Font(R.font.chakrapetch_italic, FontWeight.Light),
    Font(R.font.chakrapetch_light, FontWeight.Light),
    Font(R.font.chakrapetch_light_italic, FontWeight.ExtraLight),
    Font(R.font.chakrapetch_medium, FontWeight.Medium),
    Font(R.font.chakrapetch_medium_italic, FontWeight.SemiBold),
    Font(R.font.chakrapetch_regular, FontWeight.Normal),
    Font(R.font.chakrapetch_semibold, FontWeight.SemiBold),
    Font(R.font.chakrapetch_semibold_italic, FontWeight.SemiBold),
)

val russoOneRegular = FontFamily(
    Font(R.font.russo_one_regular, FontWeight.Normal)
)

