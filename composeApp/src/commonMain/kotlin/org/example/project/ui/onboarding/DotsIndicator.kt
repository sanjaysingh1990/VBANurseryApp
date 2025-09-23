package org.example.project.ui.onboarding

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

@Composable
fun DotsIndicator(
    totalPages: Int,
    currentPage: Int,
    modifier: Modifier = Modifier,
    dotSize: Dp = 8.dp,
    spacing: Dp = 8.dp,
    selectedColor: Color = MaterialTheme.colorScheme.primary,
    unselectedColor: Color = MaterialTheme.colorScheme.onBackground.copy(alpha = 0.3f),
    onDotClick: (index: Int) -> Unit = {}
) {
    Row(
        modifier = modifier,
        horizontalArrangement = Arrangement.Center
    ) {
        repeat(totalPages) { index ->
            val color = if (index == currentPage) selectedColor else unselectedColor
            Canvas(
                modifier = Modifier
                    .padding(spacing / 2)
                    .size(dotSize)
                    .clickable { onDotClick(index) }
            ) {
                drawRoundRect(
                    color = color,
                    size = Size(dotSize.toPx(), dotSize.toPx()),
                    cornerRadius = CornerRadius(dotSize.toPx() / 2)
                )
            }
        }
    }
}