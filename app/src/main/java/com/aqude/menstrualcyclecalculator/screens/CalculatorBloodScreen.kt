package com.aqude.menstrualcyclecalculator.screens

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
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
        TopBarInfo("Калькулятор Крови", "Оценка потери крови во время менструации за цикл менструации")
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
                    onClick = {
                        // (количество прокладок или тампонов, использованных за день)
                        // x (количество мл впитывания крови на упаковке прокладок или тампонов)
                        // x (количество дней, продолжающихся менструации)
                        // = количество крови, потерянной за цикл
                        val tamponCountsInt = tamponCounts.toIntOrNull() ?: 0
                        val tamponCountDropsInt = tamponCountDrops.toIntOrNull() ?: 0
                        val menstruationDaysCountInt = menstruationDaysCount.toIntOrNull() ?: 0
                        val resultInt = tamponCountsInt * tamponCountDropsInt * menstruationDaysCountInt
                        showTextResult = true
                        resultOnButton = "$resultInt мл"

                        if (showTextResult) {
                            CoroutineScope(Dispatchers.Main).launch {
                                delay(2000)
                                showTextResult = false
                            }
                        }
                    },
                    colors = ButtonDefaults.buttonColors(backgroundColor = MaterialTheme.colors.primary),
                    modifier = Modifier.clip(RoundedCornerShape(16.dp))
                        .width(200.dp).height(50.dp),
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

@Composable
@Preview(showBackground = true)
fun CalculatorBloodScreenPreview() {
    CalculatorBloodScreen()
}