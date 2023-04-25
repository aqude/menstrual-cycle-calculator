package com.aqude.menstrualcyclecalculator.datastore

import android.content.Context
import android.preference.Preference
import androidx.compose.material.Colors
import androidx.core.content.ContextCompat
import androidx.datastore.core.DataStore
import androidx.datastore.dataStoreFile
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.intPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import com.aqude.menstrualcyclecalculator.ui.theme.ThemeColor
import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.flow.Flow // Импортируем класс Flow для работы с потоками в Kotlin
import kotlinx.coroutines.flow.catch // Импортируем функцию catch() для перехвата и обработки ошибок в потоке
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.map // Импортируем функцию map() для преобразования элементов потока
import kotlinx.coroutines.launch
import java.io.IOException // Импортируем класс IOException для обработки исключений ввода-вывода

class StoreTheme(private val context: Context) {
    private val dataStore = context.dataStoreFile("settings")

    companion object {
        private val Context.dataStore: DataStore<Preferences> by preferencesDataStore("ThemeColor")
        private val THEME_COLOR_KEY = booleanPreferencesKey("theme_color")
    }

    val isDarkThemeEnabledFlow: Flow<Boolean> = context.dataStore.data.map { preferences ->
        preferences[THEME_COLOR_KEY] ?: false
    }

    suspend fun setIsDarkThemeEnabled(isDarkThemeEnabled: Boolean) {
        context.dataStore.edit { preferences ->
            preferences[THEME_COLOR_KEY] = isDarkThemeEnabled
        }
    }

    suspend fun getIsDarkThemeEnabled(): Boolean {
        return context.dataStore.data.first()[THEME_COLOR_KEY] ?: false
    }
}



