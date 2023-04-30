package com.aqude.menstrualcyclecalculator.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.MaterialTheme
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Switch
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.datastore.dataStore
import com.aqude.menstrualcyclecalculator.ViewModel.HomeViewModel
import com.aqude.menstrualcyclecalculator.dataStore
import com.aqude.menstrualcyclecalculator.datastore.StoreDataName
import com.aqude.menstrualcyclecalculator.ViewModel.ThemeViewModel
import kotlinx.coroutines.launch


@Composable
fun SettingsScreen() {
    // HomeViewModel : HomeViewModel
    val context = LocalContext.current

    val viewModel = remember {
        ThemeViewModel(context.dataStore)
    }
    val value = viewModel.state.observeAsState().value
    val systemInDarkTheme = isSystemInDarkTheme()

    val scope = rememberCoroutineScope()
    val DataStoreName = StoreDataName(context = context)
    var name by remember {
        mutableStateOf("")
    }

    val darkModeChecked by remember(value) {
        val checked = when (value) {
            null -> systemInDarkTheme
            else -> value
        }
        mutableStateOf(checked)
    }
    val useDeviceModeChecked by remember(value) {
        val checked = when (value) {
            null -> true
            else -> false
        }
        mutableStateOf(checked)
    }

    LaunchedEffect(viewModel) {
        viewModel.request()
    }

    Column() {
        Box(modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 20.dp)) {
            Text(text = "Настройки", Modifier.align(Alignment.Center), fontWeight = FontWeight.Bold, fontSize = 20.sp)
        }
        Box(modifier = Modifier.fillMaxWidth()) {
            // закруглениe
            Box(modifier = Modifier
                .padding(horizontal = 14.dp)
                .clip(RoundedCornerShape(16.dp))
                .background(Color.LightGray)) {
                    Row(modifier = Modifier.padding(16.dp)) {
                        Text(text = "Темная тема",
                            Modifier
                                .weight(1f)
                                .align(Alignment.CenterVertically), fontWeight = FontWeight.Bold, fontSize = 20.sp)
                        Switch(
                            checked = darkModeChecked,
                            onCheckedChange = { viewModel.switchToUseDarkMode(it) }
                        )
                    }
                }
            }
        Box(modifier = Modifier.height(20.dp))
        Box(modifier = Modifier
            .padding(horizontal = 14.dp)
            .clip(RoundedCornerShape(16.dp))
            .background(Color.LightGray)) {
            Row(modifier = Modifier.padding(16.dp)) {
                Text(text = "Системная тема",
                    Modifier
                        .weight(1f)
                        .align(Alignment.CenterVertically), fontWeight = FontWeight.Bold, fontSize = 20.sp)
                Switch(
                    checked = useDeviceModeChecked,
                    onCheckedChange = { viewModel.switchToUseSystemSettings(it) }
                )
            }
        }
        Box(modifier = Modifier.fillMaxWidth()) {
            // закруглениe
            Box(modifier = Modifier
                .padding(horizontal = 14.dp)
                .clip(RoundedCornerShape(16.dp))
                .background(Color.LightGray)) {
                Row(modifier = Modifier.padding(16.dp)) {
                    OutlinedTextField(
                        value = name,
                        onValueChange = {name = it},
                        label = { Text("Ваше имя") }
                    )
                    Button(onClick = {
                        scope.launch {
                            DataStoreName.saveDataName(name)
                        }
                    },
                        colors = ButtonDefaults.buttonColors(backgroundColor = MaterialTheme.colors.primary),
                        modifier = Modifier
                            .clip(RoundedCornerShape(16.dp))
                            .width(200.dp)
                            .height(50.dp),
                    ) {

                    }
                }
            }
        }

    }
}
