package com.aqude.menstrualcyclecalculator.ui.theme


import androidx.compose.material.Colors
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue


object AppTheme {
    private var isDarkThemeEnabled by mutableStateOf(false)

    private val themeColorData = ThemeColor.getColorsTheme()

    private val darkColors = themeColorData.darkColorPalette
    private val lightColors = themeColorData.lightColorPalette

    fun getThemeState(): Boolean {
        return isDarkThemeEnabled
    }

    fun getCurrentColorPalette(): Colors {
        return if (isDarkThemeEnabled) {
            darkColors
        } else {
            lightColors
        }
    }

    fun setThemeState(value: Boolean) {
        isDarkThemeEnabled = value
    }
}