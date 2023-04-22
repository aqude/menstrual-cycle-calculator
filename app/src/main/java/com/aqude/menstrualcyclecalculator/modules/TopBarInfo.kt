package com.aqude.menstrualcyclecalculator.modules

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Alignment.Companion.CenterVertically
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun TopBarInfo(textTitle: String, textInfo: String) {
    var infoCardState by remember {
        mutableStateOf(false)
    }
    val rotateState by animateFloatAsState(targetValue = if (infoCardState) 180f else 0f)
    Column(
        modifier = Modifier.height(150.dp)
    ) {
        Row(
            horizontalArrangement = Arrangement.Center,
        ) {
            Text(
                text = textTitle,
                fontSize = MaterialTheme.typography.h6.fontSize,
                fontWeight = FontWeight.Bold,
                modifier = Modifier
                    .weight(10f) // занимает всё доступное пространство в строке
                    .padding(start = 30.dp)
                    .align(Alignment.CenterVertically),
                textAlign = TextAlign.Center
            )
            IconButton(
                onClick = {
                    infoCardState = !infoCardState
                },
                modifier = Modifier
                    .weight(1f)// фиксированная ширина кнопки
                    .rotate(rotateState)
                    .align(Alignment.CenterVertically)
            ) {
                Icon(
                    imageVector = Icons.Default.ArrowDropDown,
                    contentDescription = "Dropdown arrow"
                )
            }
        }
        if (infoCardState) {
            Text(
                text = textInfo,
                fontWeight = FontWeight.Normal,
                maxLines = 20,
                overflow = TextOverflow.Ellipsis,
                fontSize = MaterialTheme.typography.subtitle1.fontSize,
                modifier = Modifier.padding(horizontal = 30.dp)
            )
        }
    }


}

@Composable
@Preview(showBackground = true)
fun Preview() {
    TopBarInfo("test", "ewqqqqqqqqqqqqqqqqqqqqqqqqqqq")
}