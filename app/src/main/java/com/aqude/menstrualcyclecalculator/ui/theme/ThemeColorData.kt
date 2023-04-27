package com.aqude.menstrualcyclecalculator.ui.theme

import androidx.compose.material.Colors
import androidx.compose.material.darkColors
import androidx.compose.material.lightColors


data class ThemeColorData(val darkColorPalette: Colors, val lightColorPalette: Colors)

object ThemeColor {
    private var darkColorPalette = darkColors(
        primary = Purple200,
        primaryVariant = Purple700,
        secondary = Teal200
    )

    private var lightColorPalette = lightColors(
        primary = Purple500,
        primaryVariant = Purple700,
        secondary = Teal200
        /* Other default colors to override
           background = Color.White,
           surface = Color.White,
           onPrimary = Color.White,
           onSecondary = Color.Black,
           onBackground = Color.Black,
           onSurface = Color.Black,
           */
    )
    fun getColorsTheme(): ThemeColorData {
        return ThemeColorData(darkColorPalette, lightColorPalette)
    }

}
