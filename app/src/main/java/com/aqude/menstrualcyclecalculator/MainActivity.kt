package com.aqude.menstrualcyclecalculator

import android.content.Context
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable
import androidx.datastore.preferences.preferencesDataStore
import com.aqude.menstrualcyclecalculator.ui.theme.AppTheme

//val Context.dataStore by preferencesDataStore("name")
//val Context.dataStore by preferencesDataStore("blood-ml")
val Context.dataStore by preferencesDataStore("settings")
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ThemeColor()
        }
    }
}

@Composable
fun ThemeColor() {
    AppTheme {
        MainScreen()
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



