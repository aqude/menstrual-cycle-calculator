package com.aqude.menstrualcyclecalculator.modules

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.background
import androidx.compose.foundation.horizontalScroll
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
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@Composable
fun TopBarInfo(textTitle: String, textInfo: String) {
    var infoCardState by remember {
        mutableStateOf(false)
    }
    val rotateState by animateFloatAsState(targetValue = if (infoCardState) 180f else 0f)
    Column(
        modifier = Modifier.height(150.dp), horizontalAlignment = Alignment.CenterHorizontally
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

                    if (infoCardState) {
                        CoroutineScope(Dispatchers.Main).launch {
                            delay(5000)
                            infoCardState = false
                        }
                    }
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
        AnimatedVisibility(
            visible = infoCardState,
            enter = fadeIn(),
            exit = fadeOut()
        ) {
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