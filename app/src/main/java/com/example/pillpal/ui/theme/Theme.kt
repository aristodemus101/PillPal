package com.example.pillpal.ui.theme


import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import com.example.pillpal.R

private val LightColors = lightColorScheme(
    primary = Color(0xFF2196F3), // Blue
    onPrimary = Color.White,
    background = Color.White,
    onBackground = Color.Black
)

private val DarkColors = darkColorScheme(
    primary = Color(0xFF2196F3), // Blue
    onPrimary = Color.White,
    background = Color.Black,
    onBackground = Color.White
)

val Ubunutu = FontFamily(Font(R.font.ubuntu_regular))
@Composable
fun PillPalTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
) {
    val colors = if (darkTheme) DarkColors else LightColors

    MaterialTheme(
        colorScheme = colors,
        typography = Typography,
        content = content
    )
}