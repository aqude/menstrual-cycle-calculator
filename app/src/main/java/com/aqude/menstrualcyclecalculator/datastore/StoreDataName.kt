package com.aqude.menstrualcyclecalculator.datastore

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class StoreDataName (private val context: Context) {
    companion object {
        private val Context.dataStore: DataStore<Preferences> by preferencesDataStore("NameSize")
        val DATANAME_SIZE_KEY = stringPreferencesKey("name_size")
    }

    // to get the email
    val getDataName: Flow<String?> = context.dataStore.data
        .map { preferences ->
            preferences[DATANAME_SIZE_KEY] ?: ""
        }

    // to save the email
    suspend fun saveDataName(name: String) {
        context.dataStore.edit { preferences ->
            preferences[DATANAME_SIZE_KEY] = name
        }
    }
}