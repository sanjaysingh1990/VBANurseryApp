package org.example.project

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import org.example.project.data.FakeSplashRepository
import org.example.project.domain.CheckOnboardingUseCase
import org.example.project.presentation.SplashViewModel
import org.example.project.presentation.SplashUiState
import org.example.project.ui.home.HomeScreen
import org.example.project.ui.onboarding.OnboardingScreen
import org.example.project.ui.splash.SplashScreen
import org.example.project.ui.theme.VBANurseryAppTheme
import org.jetbrains.compose.ui.tooling.preview.Preview

@Composable
@Preview
fun App() {
    VBANurseryAppTheme {
        Surface(
            modifier = Modifier.fillMaxSize(),
            color = MaterialTheme.colorScheme.background
        ) {
            val splashViewModel: SplashViewModel = viewModel {
                val repository = FakeSplashRepository()
                val useCase = CheckOnboardingUseCase(repository)
                SplashViewModel(useCase)
            }
            
            val uiState by splashViewModel.uiState.collectAsState()
            
            when (uiState) {
                SplashUiState.Loading -> {
                    SplashScreen()
                }
                SplashUiState.NavigateToHome -> {
                    HomeScreen()
                }
                SplashUiState.NavigateToOnboarding -> {
                    OnboardingScreen()
                }
            }
        }
    }
}