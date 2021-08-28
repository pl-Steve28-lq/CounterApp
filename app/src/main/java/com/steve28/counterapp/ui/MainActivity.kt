package com.steve28.counterapp.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.steve28.counterapp.state.*

class MainActivity: ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent { Main() }
    }

    @Composable
    fun Main() {
        val (state, next) = remember { mutableStateOf(CounterState(0)) }
        Surface(color = MaterialTheme.colors.background) {
            Counter(state, next)
        }
    }

    @Composable
    fun Counter(state: CounterState, next: (CounterState) -> Unit) {
        val interval = remember { mutableStateOf(1) }

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight()
                .background(Color(0xFF70A7FF)),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Components.CircleButton(
                radius = 200.dp, text = "${state.value}", size = 100.sp, color = Color.Transparent
            ) { interval.value = if (interval.value == 10000) 1 else interval.value*10 }
            Components.Space(40.dp)
            Row(horizontalArrangement = Arrangement.spacedBy(40.dp)) {
                Components.CircleButton(
                    radius = 100.dp, text = "+${interval.value}", size = 40.sp, border = 5.dp
                ) { next(state.applyEvent(CounterEvent.INCREASE(interval.value))) }
                Components.CircleButton(
                    radius = 100.dp, text = "-${interval.value}", size = 35.sp, border = 5.dp
                ) { next(state.applyEvent(CounterEvent.DECREASE(interval.value))) }
            }
            Components.Space(40.dp)
            Components.RoundButton(
                width = 180.dp, height = 70.dp, text = "Clear", color = Color.DarkGray
            ) { next(state.applyEvent(CounterEvent.CLEAR)) }
        }
    }
}

@Preview
@Composable
fun Preview() {
    MainActivity().Main()
}