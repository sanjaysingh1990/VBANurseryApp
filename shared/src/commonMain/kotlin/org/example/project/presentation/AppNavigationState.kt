package org.example.project.presentation

sealed class AppNavigationState {
    object Splash : AppNavigationState()
    object Onboarding : AppNavigationState()
    object Home : AppNavigationState()
}