package com.aqude.menstrualcyclecalculator.ui.theme

import androidx.compose.material.Colors
import androidx.compose.material.MaterialTheme
import androidx.compose.material.darkColors
import androidx.compose.material.lightColors
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.platform.LocalContext
import com.aqude.menstrualcyclecalculator.datastore.StoreTheme


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



@Composable
fun MenstrualCycleCalculatorTheme(
    content: @Composable () -> Unit
) {
    val context = LocalContext.current
    val storeTheme = remember { StoreTheme(context) }

//    val isDarkThemeEnabled by storeTheme.isDarkThemeEnabledFlow.collectAsState()

//    val colors = AppTheme.getCurrentColorPalette(storeTheme.getIsDarkThemeEnabled())
    val colors = AppTheme.getCurrentColorPalette(AppTheme.getThemeState())
    MaterialTheme(
        colors = colors,
        typography = Typography,
        shapes = Shapes,
        content = content
    )
}
