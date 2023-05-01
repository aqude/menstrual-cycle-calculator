package com.aqude.menstrualcyclecalculator.datastore

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.intPreferencesKey
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class StoreDataInfoCalendar(private val context: Context) {
    companion object {
        private val Context.dataStore: DataStore<Preferences> by preferencesDataStore("Calendar_data")

        val CYCLE_DURATION_KEY = stringPreferencesKey("cycle_duration")
        val MENSTRUATION_DURATION_KEY = stringPreferencesKey("menstruation_duration")
        val YEAR_KEY = intPreferencesKey("year")
        val MONTH_KEY = intPreferencesKey("month")
        val DAY_KEY = intPreferencesKey("day")
    }

    // to get the data
    val cycleDurationFlow: Flow<String?> = context.dataStore.data
        .map { preferences ->
            preferences[CYCLE_DURATION_KEY] ?: ""
        }

    val menstruationDurationFlow: Flow<String?> = context.dataStore.data
        .map { preferences ->
            preferences[MENSTRUATION_DURATION_KEY] ?: ""
        }

    val yearFlow: Flow<Int?> = context.dataStore.data
        .map { preferences ->
            preferences[YEAR_KEY] ?: 0
        }

    val monthFlow: Flow<Int?> = context.dataStore.data
        .map { preferences ->
            preferences[MONTH_KEY] ?: 0
        }

    val dayFlow: Flow<Int?> = context.dataStore.data
        .map { preferences ->
            preferences[DAY_KEY] ?: 0
        }

    // to save the data
    suspend fun saveCycleDuration(duration: String) {
        context.dataStore.edit { preferences ->
            preferences[CYCLE_DURATION_KEY] = duration
        }
    }

    suspend fun saveMenstruationDuration(duration: String) {
        context.dataStore.edit { preferences ->
            preferences[MENSTRUATION_DURATION_KEY] = duration
        }
    }

    suspend fun saveYear(year: Int) {
        context.dataStore.edit { preferences ->
            preferences[YEAR_KEY] = year
        }
    }

    suspend fun saveMonth(month: Int) {
        context.dataStore.edit { preferences ->
            preferences[MONTH_KEY] = month
        }
    }

    suspend fun saveDay(day: Int) {
        context.dataStore.edit { preferences ->
            preferences[DAY_KEY] = day
        }
    }

    // State objects
//    var cycleDuration by rememberSaveable { mutableStateOf("") }
//    var menstruationDuration by rememberSaveable { mutableStateOf("") }
//    var year by rememberSaveable { mutableStateOf(2023) }
//    var month by rememberSaveable { mutableStateOf(4) }
//    var day by rememberSaveable { mutableStateOf(19) }

//// Collect the values from DataStore flows
//    LaunchedEffect(Unit) {
//        cycleDurationFlow.collect { cycleDuration = it ?: "" }
//        menstruationDurationFlow.collect { menstruationDuration = it ?: "" }
//        yearFlow.collect { year = it ?: 2023 }
//        monthFlow.collect { month = it ?: 4 }
//        dayFlow.collect { day = it ?: 19 }
//    }
//
//// Save values to DataStore
//    LaunchedEffect(listOf(cycleDuration, menstruationDuration, year, month, day)) {
//        saveCycleDuration(cycleDuration)
//        saveMenstruationDuration(menstruationDuration)
//        saveYear(year)
//        saveMonth(month)
//        saveDay(day)
//    }

}