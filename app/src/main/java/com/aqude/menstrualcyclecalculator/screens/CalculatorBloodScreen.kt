package com.aqude.menstrualcyclecalculator.screens

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun CalculatorBloodScreen() {


    // количество прокладок или тампонов, использованных за день
    var tamponCounts by remember {
        mutableStateOf("")
    }
    // количество мл впитывания крови на упаковке прокладок или тампонов
    var tamponCountDrops by remember {
        mutableStateOf("")
    }
    // количество дней, продолжающихся менструации
    var menstruationDaysCount by remember {
        mutableStateOf("")
    }

    var showTextResult by remember {
        mutableStateOf(false)
    }
    Column() {
        Box(modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 20.dp)) {
            Text(text = "Калькулятор Крови", Modifier.align(Alignment.Center), fontWeight = FontWeight.Bold, fontSize = 20.sp)
        }
            // потеря крови во время менструации за цикл

            Column(modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 14.dp), verticalArrangement = Arrangement.spacedBy(20.dp), horizontalAlignment = Alignment.CenterHorizontally) {

                OutlinedTextField(value = tamponCounts, onValueChange = { newValue ->
                        tamponCounts = newValue
                    }, label = { Text("Количество тампонов") })

                    OutlinedTextField(value = tamponCountDrops, onValueChange = { newValue ->
                        tamponCountDrops = newValue
                    }, label = { Text("Количество капель на тампоне") })

                OutlinedTextField(value = menstruationDaysCount, onValueChange = { newValue ->
                        menstruationDaysCount = newValue
                    }, label = { Text("Количество дней менструации") })


                // (количество прокладок или тампонов, использованных за день)
                // x (количество мл впитывания крови на упаковке прокладок или тампонов)
                // x (количество дней, продолжающихся менструации)
                // = количество крови, потерянной за цикл
                // Проверяем переменные на null и конвертируем их в Int
                val tamponCountsInt = tamponCounts.toIntOrNull() ?: 0
                val tamponCountDropsInt = tamponCountDrops.toIntOrNull() ?: 0
                val menstruationDaysCountInt = menstruationDaysCount.toIntOrNull() ?: 0

                // Вычисляем сумму
                val resultInt = tamponCountsInt * tamponCountDropsInt * menstruationDaysCountInt
                var resultOnButton by remember {
                    mutableStateOf("")
                }
                ExtendedFloatingActionButton(
                    backgroundColor = MaterialTheme.colors.primary,
                    onClick = {
                        showTextResult = true
                        resultOnButton = resultInt.toString()
                              },
                    text = { Text(text = "Рассчитать")}
                )
//                val result: Int = tamponCounts.toInt() * tamponCountDrops.toInt() * menstruationDaysCount.toInt()


                //  количество крови, потерянной за цикл

                    AnimatedVisibility(
                        visible = showTextResult,
                        enter = fadeIn(),
                        exit = fadeOut()
                    ) {
                        Box(modifier = Modifier
                            .clip(RoundedCornerShape(16.dp))
                            .background(Color.LightGray)
                            .padding(16.dp)
                        ) {
                            Text(text = "$resultOnButton мл")
                        }
                    }

            }
    }
}

@Composable
@Preview
fun CalculatorBloodScreenPreview() {
    CalculatorBloodScreen()
}