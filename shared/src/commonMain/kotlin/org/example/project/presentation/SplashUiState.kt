package org.example.project.presentation

sealed class SplashUiState {
    object Loading : SplashUiState()
    object NavigateToHome : SplashUiState()
    object NavigateToOnboarding : SplashUiState()
}