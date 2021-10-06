package tech.developingdeveloper.exploringjetpackcompose

import androidx.compose.ui.graphics.painter.Painter

data class CardImage(
    val title: String,
    val painter: Painter,
    val contentDescription: String
)