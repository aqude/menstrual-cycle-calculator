package com.aqude.menstrualcyclecalculator.screens

import android.app.DatePickerDialog
import android.widget.Toast
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.MaterialTheme
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import com.aqude.menstrualcyclecalculator.datastore.StoreDataInfoCalendar
import com.aqude.menstrualcyclecalculator.modules.TopBarInfo
import kotlinx.coroutines.launch

@Composable
fun InfoScreen() {
    val context = LocalContext.current

    val scope = rememberCoroutineScope()
    val dataStore = StoreDataInfoCalendar(context)

    var cycleDuration by rememberSaveable() {
        mutableStateOf("")
    }
    var menstruationDuration by rememberSaveable() {
        mutableStateOf("")
    }
    var year by rememberSaveable { mutableStateOf(0) }
    var month by rememberSaveable { mutableStateOf(0) }
    var day by rememberSaveable { mutableStateOf(0) }

    val onDateSelected = DatePickerDialog.OnDateSetListener { _, newYear, newMonth, newDay ->
        year = newYear
        month = newMonth
        day = newDay
    }



    Column() {
        TopBarInfo(
            "Информация",
            "Ввод данных в календарь масячных",
            50
        )

        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState())
        ) {
            OutlinedTextField(
                value = cycleDuration,
                onValueChange = { newValue -> cycleDuration = newValue },
                label = { Text("Длительность цикла") }
            )
            Box(modifier = Modifier.height(10.dp))
            OutlinedTextField(
                value = menstruationDuration,
                onValueChange = { newValue -> menstruationDuration = newValue },
                label = { Text("Длительность менструации") }
            )
            Box(modifier = Modifier.height(15.dp))
            Button(
                onClick = {
                    DatePickerDialog(
                        context,
                        onDateSelected,
                        year,
                        month,
                        day
                    ).show()
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 66.dp)
                    .height(55.dp)
            ) {
                Text("День последней менструации")
            }
            Box(modifier = Modifier.height(10.dp))
            Text(
                text = "Дата: $day-$month-$year"
            )
            Box(modifier = Modifier.height(10.dp))
            Button(
                enabled = cycleDuration.isNotBlank() && menstruationDuration.isNotBlank(),
                onClick = {
                    scope.launch {
                        dataStore.saveDay(day)
                        dataStore.saveMonth(month)
                        dataStore.saveYear(year)
                        dataStore.saveCycleDuration(cycleDuration)
                        dataStore.saveMenstruationDuration(menstruationDuration)
                    }
                    Toast.makeText(
                        context,
                        "Отправленные данные: $day $month $year $cycleDuration $menstruationDuration",
                        Toast.LENGTH_SHORT
                    ).show()
                },
                colors = ButtonDefaults.buttonColors(backgroundColor = MaterialTheme.colors.primary),
                modifier = Modifier
                    .clip(RoundedCornerShape(16.dp))
                    .width(200.dp)
                    .height(50.dp),
            ) {
                Text(text = "Подтвердить данные")
            }
        }


    }
}