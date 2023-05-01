package com.aqude.menstrualcyclecalculator.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.MaterialTheme
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Switch
import androidx.compose.material.Text
import androidx.compose.material.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.aqude.menstrualcyclecalculator.ViewModel.ThemeViewModel
import com.aqude.menstrualcyclecalculator.dataStore
import com.aqude.menstrualcyclecalculator.datastore.StoreDataName
import com.aqude.menstrualcyclecalculator.modules.TopBarInfo
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
    val dataStoreName = StoreDataName(context = context)
    var name by rememberSaveable() {
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
//        Box(
//            modifier = Modifier
//                .fillMaxWidth()
//                .padding(vertical = 20.dp)
//        ) {
//            Text(
//                text = "Настройки",
//                Modifier.align(Alignment.Center),
//                fontWeight = FontWeight.Bold,
//                fontSize = 20.sp
//            )
//        }
        TopBarInfo(
            "Настройки",
            "Настройка приложения",
            80
        )

        Box(modifier = Modifier.fillMaxWidth()) {
            // закруглениe
            Box(
                modifier = Modifier
                    .padding(horizontal = 14.dp)
                    .clip(RoundedCornerShape(16.dp))
                    .background(Color.LightGray)
            ) {
                Row(modifier = Modifier.padding(16.dp)) {
                    Text(
                        text = "Темная тема",
                        Modifier
                            .weight(1f)
                            .align(Alignment.CenterVertically),
                        fontWeight = FontWeight.Bold,
                        fontSize = 20.sp
                    )
                    Switch(
                        checked = darkModeChecked,
                        onCheckedChange = { viewModel.switchToUseDarkMode(it) }
                    )
                }
            }
        }
        Box(modifier = Modifier.height(20.dp))
        Box(
            modifier = Modifier
                .padding(horizontal = 14.dp)
                .clip(RoundedCornerShape(16.dp))
                .background(Color.LightGray)
        ) {
            Row(modifier = Modifier.padding(16.dp)) {
                Text(
                    text = "Системная тема",
                    Modifier
                        .weight(1f)
                        .align(Alignment.CenterVertically),
                    fontWeight = FontWeight.Bold,
                    fontSize = 20.sp
                )
                Switch(
                    checked = useDeviceModeChecked,
                    onCheckedChange = { viewModel.switchToUseSystemSettings(it) }
                )
            }
        }

        Box(modifier = Modifier.height(20.dp))
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 30.dp),
        ) {
            Column() {
                Text(
                    text = "Ваше имя",
                    Modifier.padding(bottom = 20.dp),
                    fontWeight = FontWeight.Bold,
                    fontSize = 20.sp
                )
                Row(
                    modifier = Modifier,
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween // указываем разделение по горизонтали
                ) {
                    OutlinedTextField(
                        value = name,
                        onValueChange = { newText -> name = newText },
                        modifier = Modifier
                            .weight(1f) // занимает всю доступную ширину
                            .padding(end = 8.dp), // добавляем отступ справа
                        textStyle = TextStyle(color = Color.Black),
                        colors = TextFieldDefaults.outlinedTextFieldColors(
                            focusedBorderColor = Color.LightGray,
                            //   unfocusedBorderColor = Color.LightGray
                        )
                    )
                    Box(modifier = Modifier.width(30.dp))
                    Button(
                        enabled = name.isNotBlank(),
                        onClick = {
                            scope.launch {
                                dataStoreName.saveDataName(name)
                            }
                        },
                        colors = ButtonDefaults.buttonColors(backgroundColor = MaterialTheme.colors.primary),
                        modifier = Modifier
                            .clip(RoundedCornerShape(16.dp))
                            .width(120.dp)
                            .height(50.dp)
                    ) {
                        Text(text = "Добавить")
                    }
                }
            }

        }
    }
}

@Composable
@Preview(showBackground = true)
fun PreviewSettingsScreen() {
    SettingsScreen()
}