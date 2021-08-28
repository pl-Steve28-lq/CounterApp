package com.steve28.counterapp.ui

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.OutlinedButton
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawWithContent
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.foundation.layout.*

object Components {
    @Composable
    fun AutoSizeText(
        text: String, size: TextUnit,
        modifier: Modifier = Modifier
    ) {
        val sp = remember(text) { mutableStateOf(size) }
        val readyToDraw = remember(text) { mutableStateOf(false) }

        Text(
            text,
            modifier.drawWithContent { if (readyToDraw.value) drawContent() },
            style = TextStyle(fontSize = sp.value),
            softWrap = false,
            onTextLayout = { textLayoutResult ->
                if (textLayoutResult.didOverflowWidth) { sp.value = sp.value * 0.9 }
                else { readyToDraw.value = true }
            }
        )
    }

    @Composable
    fun BaseButton(
        modifier: Modifier = Modifier, text: String,
        color: Color, size: TextUnit,
        border: Dp, onClick: () -> Unit
    ) {
        OutlinedButton(
            onClick = onClick,
            modifier = modifier,
            shape = CircleShape,
            border = BorderStroke(border, Color.White),
            contentPadding = PaddingValues(0.dp),
            colors = ButtonDefaults.outlinedButtonColors(
                backgroundColor = color,
                contentColor = Color.White
            )
        ) { AutoSizeText(text, size) }
    }

    @Composable
    fun RoundButton(
        width: Dp, height: Dp, text: String,
        color: Color = Color.Black.copy(alpha = 0.5f),
        size: TextUnit = 30.sp, onClick: () -> Unit
    ) {
        BaseButton(
            modifier = Modifier.width(width).height(height),
            text = text,
            size = size,
            border = 5.dp,
            color = color,
            onClick = onClick
        )
    }

    @Composable
    fun CircleButton(
        radius: Dp, text: String, size: TextUnit = 30.sp,
        color: Color = Color.Black.copy(alpha = 0.5f),
        border: Dp = 2.dp, onClick: () -> Unit = {}
    ) {
        BaseButton(
            modifier = Modifier.size(radius),
            text = text,
            size = size,
            color = color,
            border = border,
            onClick = onClick
        )
    }

    @Composable
    fun Space(height: Dp) {
        Spacer(modifier = Modifier.height(height))
    }
}