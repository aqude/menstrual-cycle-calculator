package com.aqude.menstrualcyclecalculator.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Switch
import androidx.compose.material.Text
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
import com.aqude.menstrualcyclecalculator.ui.theme.AppTheme


@Composable
fun SettingsScreen() {
    var isDarkThemeEnabled by rememberSaveable() {
        mutableStateOf(AppTheme.getThemeState())
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
                        checked = isDarkThemeEnabled,
                        onCheckedChange = { isDarkThemeEnabled = it
                            isDarkThemeEnabled = it
                            AppTheme.setThemeState(it)}
                    )
                }
            }
        }
    }
}

@Preview
@Composable
fun PreviewFun() {
    SettingsScreen()
}