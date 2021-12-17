package dev.victorhernandez.tvshows.presentation.theme

import androidx.compose.material.darkColors
import androidx.compose.material.lightColors
import androidx.compose.ui.graphics.Color

val PurpleLight = Color(0xFFAA6EFF)
val Purple = Color(0xFF6200EA)
val PurpleDark = Color(0xFF270952)
val PurpleBlack = Color(0xFF1B0D2E)
val Orange = Color(0xFFFFC107)
val OrangeDark = Color(0xFFC67C00)
val Red = Color(0xFFDD2C00)

val LightColorPalette = lightColors(
    primary = Purple,
    primaryVariant = PurpleDark,
    secondary = Orange,
    secondaryVariant = OrangeDark,
    error = Red,
    onError = Color.White
)

val DarkColorPalette = darkColors(
    primary = PurpleLight,
    primaryVariant = Purple,
    secondary = Orange,
    secondaryVariant = OrangeDark,
    error = Red,
    background = PurpleBlack,
    surface = PurpleDark,
    onError = Color.White,
)