package com.aqude.menstrualcyclecalculator.ViewModel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel

class HomeViewModel : ViewModel() {
    var nameDataState by mutableStateOf("")
    var mlBloodSize by mutableStateOf("")

    // events
    fun OnNameChanged(value: String) {
        nameDataState = value
    }
    fun OnBloodChanged(value: String) {
        mlBloodSize = value
    }
}