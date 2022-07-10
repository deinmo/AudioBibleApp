package com.deinmo.audiobibleapp.feature_bible_catalog.presentation.ui

import android.os.Build
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import javax.annotation.meta.When

private val DarkColorPalette = darkColorScheme(
    primary = Purple80,
    onPrimary = Purple10,
    primaryContainer = Purple20,
    onPrimaryContainer = Purple90,
    inversePrimary = Purple40,
    secondary = blue80,
    onSecondary = blue10,
    secondaryContainer = blue20,
    onSecondaryContainer = blue90,
    tertiary = Teal80,
    onTertiary = Teal10,
    tertiaryContainer = Teal20,
    onTertiaryContainer = Teal90,
    error = red80,
    onError = red10,
    errorContainer = red20,
    onErrorContainer = red90,
    background = Grey10,
    onBackground = Grey90,
    surface = Purpleblue40,
    onSurface = Purpleblue80,
    inverseSurface = Grey90,
    inverseOnSurface = Grey10,
    surfaceVariant = Purpleblue40,
    onSurfaceVariant = Purpleblue80,
    outline = Purpleblue80
)

private val LightColorPalette = lightColorScheme(
    primary = Purple20,
    onPrimary = Color.White,
    primaryContainer = Purple90,
    onPrimaryContainer = Purple10,
    inversePrimary = Purple80,
    secondary = blue20,
    onSecondary = Color.White,
    secondaryContainer = blue80,
    onSecondaryContainer = blue10,
    tertiary = Teal20,
    onTertiary = Color.White,
    tertiaryContainer = Teal80,
    onTertiaryContainer = Teal10,
    error = red20,
    onError = Color.White,
    errorContainer = red80,
    onErrorContainer = red10,
    background = Grey90,
    onBackground = Grey10,
    surface = Purpleblue90,
    onSurface = Purpleblue20,
    inverseSurface = Grey20,
    inverseOnSurface = Grey90,
    surfaceVariant = Purpleblue80,
    onSurfaceVariant = Purpleblue40,
    outline = Purpleblue20

    /* Other default colors to override
    background = Color.White,
    surface = Color.White,
    onPrimary = Color.White,
    onSecondary = Color.Black,
    onBackground = Color.Black,
    onSurface = Color.Black,
    */
)

@Composable
fun AudioBibleAppTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
) {
    val usedynamicccolors = Build.VERSION.SDK_INT >= Build.VERSION_CODES.S
    val colors = when{
        usedynamicccolors && darkTheme -> dynamicDarkColorScheme(LocalContext.current)
        usedynamicccolors && !darkTheme -> dynamicLightColorScheme(LocalContext.current)
        darkTheme -> DarkColorPalette
        else -> LightColorPalette
    }

    MaterialTheme(
        colorScheme = colors,
        typography = Typography,
        shapes = Shapes,
        content = content
    )
}