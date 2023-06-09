package com.aqude.menstrualcyclecalculator.screens

import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import com.aqude.menstrualcyclecalculator.datastore.StoreDateBloodSize
import com.aqude.menstrualcyclecalculator.modules.TopBarInfo
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@Composable
fun CalculatorBloodScreen() {
    Column() {
// Калькулятор Крови
        // потеря крови во время менструации за цикл
        val context = LocalContext.current
        val scope = rememberCoroutineScope()
        val dataStore = StoreDateBloodSize(context)


        TopBarInfo(
            "Калькулятор Крови",
            "Оценка потери крови во время менструации за цикл менструации",
            50
        )
        Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
            var tamponCounts by rememberSaveable { mutableStateOf("") }
            var tamponCountDrops by rememberSaveable { mutableStateOf("") }
            var menstruationDaysCount by rememberSaveable { mutableStateOf("") }
            var showTextResult by rememberSaveable { mutableStateOf(false) }
            var resultOnButton by rememberSaveable { mutableStateOf("") }

            Column(
                modifier = Modifier
                    .padding(horizontal = 14.dp)
                    .padding(bottom = 100.dp)
                    .fillMaxSize(),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                OutlinedTextField(
                    value = tamponCounts,
                    onValueChange = { newValue -> tamponCounts = newValue },
                    label = { Text("Количество тампонов") }
                )
                Box(modifier = Modifier.height(10.dp))
                OutlinedTextField(
                    value = tamponCountDrops,
                    onValueChange = { newValue -> tamponCountDrops = newValue },
                    label = { Text("Количество капель на тампоне") }
                )
                Box(modifier = Modifier.height(10.dp))
                OutlinedTextField(
                    value = menstruationDaysCount,
                    onValueChange = { newValue -> menstruationDaysCount = newValue },
                    label = { Text("Количество дней менструации") }
                )
                Box(modifier = Modifier.height(20.dp))
                Button(
                    enabled = tamponCounts.isNotBlank() && tamponCountDrops.isNotBlank() && menstruationDaysCount.isNotBlank(),
                    onClick = {
                        // (количество прокладок или тампонов, использованных за день)
                        // x (количество мл впитывания крови на упаковке прокладок или тампонов)
                        // x (количество дней, продолжающихся менструации)
                        // = количество крови, потерянной за цикл
                        val tamponCountsInt = tamponCounts.toIntOrNull() ?: 0
                        val tamponCountDropsInt = tamponCountDrops.toIntOrNull() ?: 0
                        val menstruationDaysCountInt = menstruationDaysCount.toIntOrNull() ?: 0
                        val resultInt =
                            tamponCountsInt * tamponCountDropsInt * menstruationDaysCountInt
                        showTextResult = true
                        resultOnButton = "$resultInt мл"
                        scope.launch {
                            dataStore.saveDataBloodSize(resultInt.toString())
                        }

                        if (showTextResult) {
                            CoroutineScope(Dispatchers.Main).launch {
                                delay(2000)
                                showTextResult = false
                            }
                        }
                    },
                    colors = ButtonDefaults.buttonColors(backgroundColor = MaterialTheme.colors.primary),
                    modifier = Modifier
                        .clip(RoundedCornerShape(16.dp))
                        .width(200.dp)
                        .height(50.dp),
                ) {
                    Text("Рассчитать")
                }
                Box(modifier = Modifier.height(10.dp))
                Box(modifier = Modifier.height(50.dp)) {
                    androidx.compose.animation.AnimatedVisibility(
                        visible = showTextResult,
                        enter = fadeIn(),
                        exit = fadeOut()
                    ) {
                        Box(
                            modifier = Modifier
                                .clip(RoundedCornerShape(16.dp))
                                .background(Color.LightGray)
                                .padding(16.dp)
                                .width(170.dp),
                            contentAlignment = Alignment.Center
                        ) {
                            Text(text = resultOnButton)
                        }
                    }
                }

            }
        }
    }
}
