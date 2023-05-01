package com.aqude.menstrualcyclecalculator.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.aqude.menstrualcyclecalculator.datastore.StoreDataInfoCalendar
import com.aqude.menstrualcyclecalculator.datastore.StoreDataName
import com.aqude.menstrualcyclecalculator.datastore.StoreDateBloodSize
import com.aqude.menstrualcyclecalculator.modules.TopBarInfo

@Composable
fun HomeScreen() {
// HomeViewModel : HomeViewModel
    val context = LocalContext.current

    val dataStoreName = StoreDataName(context = context)
    val dataStoreBlood = StoreDateBloodSize(context = context)
    val dataStoreCalendar = StoreDataInfoCalendar(context = context)

    val saveBlood = dataStoreBlood.getDataBloodSize.collectAsState(initial = "")
    val saveName = dataStoreName.getDataName.collectAsState(initial = "")
    val cycle = dataStoreCalendar.cycleDurationFlow.collectAsState(initial = "").value
    val menstrual = dataStoreCalendar.menstruationDurationFlow.collectAsState(initial = "").value
    val year = dataStoreCalendar.yearFlow.collectAsState(initial = "").value
    val month = dataStoreCalendar.monthFlow.collectAsState(initial = "").value
    val day = dataStoreCalendar.dayFlow.collectAsState(initial = "").value

    // Сколько дней
    Column(modifier = Modifier.fillMaxSize(), horizontalAlignment = Alignment.CenterHorizontally) {
        TopBarInfo(
            "Информация",
            "Полная информация о здоровье",
            70
        )


        Box(modifier = Modifier.fillMaxWidth()) {
            // закруглениe
            Box(
                modifier = Modifier
                    .padding(vertical = 20.dp, horizontal = 20.dp)
                    .clip(RoundedCornerShape(16.dp))
                    .background(Color.LightGray)
            ) {
                Box(modifier = Modifier) {
                    Column(modifier = Modifier.padding(20.dp)) {
                        Row() {
                            Text(
                                text = "Привет, ${saveName.value} !",
                                Modifier
                                    .align(Alignment.CenterVertically),
                                fontWeight = FontWeight.Bold,
                                fontSize = 20.sp
                            )
                        }
                        Box(modifier = Modifier.height(5.dp))
                        Row() {
                            Text(
                                text = "Колличество крови: ${saveBlood.value} мл.",
                                Modifier
                                    .align(Alignment.CenterVertically),
                                fontWeight = FontWeight.Bold,
                                fontSize = 20.sp
                            )
                        }
                    }
                }
            }
        }


    }
//        Row(Modifier.fillMaxWidth()) {
//
//        }
//        Row(Modifier.fillMaxWidth()) {
//
//        }
//    }

}
