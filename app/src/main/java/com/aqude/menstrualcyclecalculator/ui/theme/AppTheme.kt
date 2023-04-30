package com.aqude.menstrualcyclecalculator.ui.theme


import android.annotation.SuppressLint
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material.Colors
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.ReadOnlyComposable
import androidx.compose.runtime.compositionLocalOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.platform.LocalContext
import com.aqude.menstrualcyclecalculator.ViewModel.ThemeViewModel
import com.aqude.menstrualcyclecalculator.dataStore


object AppTheme {
    private var isDarkThemeEnabled by mutableStateOf(false)

    private val themeColorData = ThemeColor.getColorsTheme()
    private val darkColors = themeColorData.darkColorPalette
    private val lightColors = themeColorData.lightColorPalette

    fun getThemeState(): Boolean {
        return isDarkThemeEnabled
    }

    fun getCurrentColorPalette(isDarkThemeEnabled: Boolean): Colors {
        return if (isDarkThemeEnabled) {
            darkColors
        } else {
            lightColors
        }
    }

    fun setThemeState(it: Boolean) {
        isDarkThemeEnabled = it
    }
}

@Composable
fun AppTheme(
    content: @Composable () -> Unit
) {

    val themeColorData = ThemeColor.getColorsTheme()

    val darkColors = themeColorData.darkColorPalette
    val lightColors = themeColorData.lightColorPalette

    val context = LocalContext.current
    val viewModel = remember { ThemeViewModel(context.dataStore) }
    val state = viewModel.state.observeAsState()
    val value = state.value ?: isSystemInDarkTheme()

    LaunchedEffect(viewModel) { viewModel.request() }

    DarkThemeValue.current.value = value

    MaterialTheme(
        colors = if (value) darkColors else lightColors,
        typography = Typography,
        shapes = Shapes,
        content = content
    )
}
@Composable
@ReadOnlyComposable
fun isDarkTheme() = DarkThemeValue.current.value

@SuppressLint("CompositionLocalNaming")
private val DarkThemeValue = compositionLocalOf { mutableStateOf(false) }

@Composable
@ReadOnlyComposable
infix fun <T> T.orInLightTheme(other: T): T = if (isDarkTheme()) this else other