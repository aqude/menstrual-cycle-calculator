package com.aqude.menstrualcyclecalculator

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.aqude.menstrualcyclecalculator.datastore.StoreTheme
import com.aqude.menstrualcyclecalculator.ui.theme.MenstrualCycleCalculatorTheme
import kotlinx.coroutines.launch

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MenstrualCycleCalculatorTheme {
                MainScreen()
            }
        }
    }
}

//class MyViewModel : ViewModel() {
//    private val storeTheme: StoreTheme = StoreTheme(applicationContext)
//
//    fun setThemeState(isDarkThemeEnabled: Boolean) {
//        viewModelScope.launch {
//            storeTheme.setIsDarkThemeEnabled(isDarkThemeEnabled)
//        }
//    }
//}



