package org.example.project.ui.splash

import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.unit.dp

@Composable
fun AnimatedFlower(
    modifier: Modifier = Modifier,
    color: Color = Color(0xFF1dc962) // Primary green color
) {
    val animationProgress = remember { Animatable(0f) }
    
    LaunchedEffect(Unit) {
        animationProgress.animateTo(
            targetValue = 1f,
            animationSpec = tween(
                durationMillis = 2000,
                easing = LinearEasing
            )
        )
    }
    
    Canvas(modifier = modifier.size(192.dp)) {
        val center = Offset(size.width / 2f, size.height / 2f)
        val radius = size.minDimension / 2f * animationProgress.value
        
        // Draw petals
        val petalCount = 8
        for (i in 0 until petalCount) {
            val angle = (360f / petalCount) * i
            val petalPath = Path()
            
            // Create petal shape
            val startAngleRad = Math.toRadians(angle.toDouble())
            val endAngleRad = Math.toRadians((angle + 45).toDouble())
            
            val startPoint = Offset(
                center.x + radius * 0.5f * kotlin.math.cos(startAngleRad.toFloat()),
                center.y + radius * 0.5f * kotlin.math.sin(startAngleRad.toFloat())
            )
            
            val endPoint = Offset(
                center.x + radius * kotlin.math.cos(startAngleRad.toFloat()),
                center.y + radius * kotlin.math.sin(startAngleRad.toFloat())
            )
            
            val controlPoint = Offset(
                center.x + radius * 0.8f * kotlin.math.cos(Math.toRadians(angle + 22.5).toFloat()),
                center.y + radius * 0.8f * kotlin.math.sin(Math.toRadians(angle + 22.5).toFloat())
            )
            
            petalPath.moveTo(startPoint.x, startPoint.y)
            petalPath.quadraticBezierTo(
                controlPoint.x, controlPoint.y,
                endPoint.x, endPoint.y
            )
            
            drawPath(
                path = petalPath,
                color = color,
                style = Stroke(width = 4f * animationProgress.value)
            )
        }
        
        // Draw center circle
        if (animationProgress.value > 0.5f) {
            drawCircle(
                color = color,
                radius = radius * 0.2f,
                center = center
            )
        }
    }
}