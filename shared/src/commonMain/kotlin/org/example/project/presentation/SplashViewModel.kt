package org.example.project.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import org.example.project.domain.CheckOnboardingUseCase

class SplashViewModel(
    private val checkOnboardingUseCase: CheckOnboardingUseCase
) : ViewModel() {
    
    private val _uiState = MutableStateFlow<SplashUiState>(SplashUiState.Loading)
    val uiState: StateFlow<SplashUiState> = _uiState.asStateFlow()
    
    private companion object {
        const val SPLASH_DURATION = 5000L // 5 seconds
    }
    
    init {
        viewModelScope.launch {
            // Show splash screen for defined duration
            delay(SPLASH_DURATION)
            
            // Check if we should show onboarding or go directly to home
            val shouldShowOnboarding = checkOnboardingUseCase().getOrNull() ?: false
            
            _uiState.value = if (shouldShowOnboarding) {
                SplashUiState.NavigateToOnboarding
            } else {
                SplashUiState.NavigateToHome
            }
        }
    }
}