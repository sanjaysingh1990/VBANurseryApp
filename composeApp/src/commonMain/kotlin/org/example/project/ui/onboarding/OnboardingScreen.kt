package org.example.project.ui.onboarding

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.Orientation
import androidx.compose.foundation.gestures.draggable
import androidx.compose.foundation.gestures.rememberDraggableState
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import org.example.project.data.FakeOnboardingRepository
import org.example.project.domain.GetOnboardingPagesUseCase
import org.example.project.presentation.OnboardingViewModel

@Composable
fun OnboardingScreen(
    modifier: Modifier = Modifier,
    onNavigateToHome: () -> Unit = {},
    viewModel: OnboardingViewModel = viewModel {
        val repository = FakeOnboardingRepository()
        val useCase = GetOnboardingPagesUseCase(repository)
        OnboardingViewModel(useCase, onNavigateToHome)
    }
) {
    val uiState by viewModel.uiState.collectAsState()
    
    if (uiState.isLoading) {
        Box(
            modifier = modifier.fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            Text("Loading...")
        }
        return
    }
    
    var dragOffset by remember { mutableStateOf(0f) }
    val dragThreshold = 200f // Minimum drag distance to trigger page change
    
    Box(
        modifier = modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
            .draggable(
                state = rememberDraggableState { delta ->
                    dragOffset += delta
                },
                orientation = Orientation.Horizontal,
                onDragStopped = {
                    if (dragOffset > dragThreshold && uiState.currentPageIndex > 0) {
                        viewModel.onPreviousPage()
                    } else if (dragOffset < -dragThreshold && uiState.currentPageIndex < uiState.totalPages - 1) {
                        viewModel.onNextPage()
                    }
                    dragOffset = 0f
                }
            )
    ) {
        Column(
            modifier = Modifier.fillMaxSize()
        ) {
            // Hero section (top 3/5 height)
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .fillMaxHeight(0.6f)
                    .background(MaterialTheme.colorScheme.primary)
            ) {
                // Background image with gradient overlay
                // Temporarily use a simple colored box instead of image resource
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(Color(0xFF1dc962)) // Green color similar to the image
                )
                
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(
                            color = if (MaterialTheme.colorScheme.background == Color(0xFFf6f8f7)) {
                                Color.Black.copy(alpha = 0.3f)
                            } else {
                                Color.Black.copy(alpha = 0.5f)
                            }
                        )
                )
                
                // Page counter
                Box(
                    modifier = Modifier
                        .align(Alignment.TopEnd)
                        .padding(16.dp)
                        .background(
                            color = Color.White.copy(alpha = 0.8f),
                            shape = RoundedCornerShape(16.dp)
                        )
                        .padding(horizontal = 12.dp, vertical = 6.dp)
                ) {
                    Text(
                        text = "${uiState.currentPageIndex + 1}/${uiState.totalPages}",
                        color = MaterialTheme.colorScheme.onPrimary,
                        fontWeight = FontWeight.Medium
                    )
                }
                
                // Navigation arrows
                if (uiState.currentPageIndex > 0) {
                    androidx.compose.material3.IconButton(
                        onClick = { viewModel.onPreviousPage() },
                        modifier = Modifier
                            .align(Alignment.CenterStart)
                            .padding(16.dp)
                    ) {
                        // Using a simple text arrow since we're avoiding resource dependencies
                        Text(
                            text = "<",
                            color = Color.White,
                            fontSize = 24.sp,
                            fontWeight = FontWeight.Bold
                        )
                    }
                }
                
                if (uiState.currentPageIndex < uiState.totalPages - 1) {
                    androidx.compose.material3.IconButton(
                        onClick = { viewModel.onNextPage() },
                        modifier = Modifier
                            .align(Alignment.CenterEnd)
                            .padding(16.dp)
                    ) {
                        // Using a simple text arrow since we're avoiding resource dependencies
                        Text(
                            text = ">",
                            color = Color.White,
                            fontSize = 24.sp,
                            fontWeight = FontWeight.Bold
                        )
                    }
                }
            }
            
            // Content section (bottom 2/5 height)
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .fillMaxHeight()
                    .padding(horizontal = 24.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.SpaceBetween
            ) {
                Spacer(Modifier.height(24.dp))
                
                // Title and subtitle
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    uiState.currentPage?.let { page ->
                        Text(
                            text = page.title,
                            fontSize = 24.sp,
                            fontWeight = FontWeight.Bold,
                            color = MaterialTheme.colorScheme.onBackground,
                            textAlign = TextAlign.Center
                        )
                        
                        Spacer(Modifier.height(16.dp))
                        
                        Text(
                            text = page.subtitle,
                            fontSize = 16.sp,
                            color = MaterialTheme.colorScheme.onBackground.copy(alpha = 0.7f),
                            textAlign = TextAlign.Center
                        )
                    }
                }
                
                Spacer(Modifier.height(16.dp))
                
                // Dots indicator
                DotsIndicator(
                    totalPages = uiState.totalPages,
                    currentPage = uiState.currentPageIndex,
                    onDotClick = { index -> viewModel.onDotClick(index) }
                )
                
                Spacer(Modifier.height(16.dp))
                
                // Buttons
                Column(
                    modifier = Modifier.fillMaxWidth(),
                    verticalArrangement = Arrangement.spacedBy(12.dp)
                ) {
                    Button(
                        onClick = { viewModel.onGetStartedClick() },
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(48.dp)
                            .clip(RoundedCornerShape(8.dp)),
                        colors = ButtonDefaults.buttonColors(
                            containerColor = MaterialTheme.colorScheme.primary,
                            contentColor = Color.White
                        )
                    ) {
                        Text(
                            text = "Get Started",
                            fontSize = 16.sp,
                            fontWeight = FontWeight.Medium
                        )
                    }
                    
                    OutlinedButton(
                        onClick = { viewModel.onSignInClick() },
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(48.dp)
                            .clip(RoundedCornerShape(8.dp)),
                        colors = ButtonDefaults.outlinedButtonColors(
                            contentColor = MaterialTheme.colorScheme.onBackground
                        )
                    ) {
                        Text(
                            text = "Sign In",
                            fontSize = 16.sp,
                            fontWeight = FontWeight.Medium
                        )
                    }
                }
                
                Spacer(Modifier.height(24.dp))
            }
        }
    }
}