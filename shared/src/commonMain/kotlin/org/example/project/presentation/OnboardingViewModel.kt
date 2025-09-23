package org.example.project.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import org.example.project.domain.GetOnboardingPagesUseCase

class OnboardingViewModel(
    private val getOnboardingPagesUseCase: GetOnboardingPagesUseCase,
    private val onNavigateToHome: () -> Unit = {}
) : ViewModel() {
    
    private val _uiState = MutableStateFlow(OnboardingUiState())
    val uiState: StateFlow<OnboardingUiState> = _uiState.asStateFlow()
    
    init {
        loadOnboardingPages()
    }
    
    private fun loadOnboardingPages() {
        viewModelScope.launch {
            _uiState.value = _uiState.value.copy(isLoading = true)
            try {
                val pages = getOnboardingPagesUseCase(Unit).getOrNull() ?: emptyList()
                _uiState.value = _uiState.value.copy(
                    pages = pages,
                    isLoading = false
                )
            } catch (e: Exception) {
                _uiState.value = _uiState.value.copy(isLoading = false)
            }
        }
    }
    
    fun onNextPage() {
        val currentState = _uiState.value
        if (currentState.currentPageIndex < currentState.totalPages - 1) {
            _uiState.value = currentState.copy(currentPageIndex = currentState.currentPageIndex + 1)
        }
    }
    
    fun onPreviousPage() {
        val currentState = _uiState.value
        if (currentState.currentPageIndex > 0) {
            _uiState.value = currentState.copy(currentPageIndex = currentState.currentPageIndex - 1)
        }
    }
    
    fun onDotClick(index: Int) {
        if (index >= 0 && index < _uiState.value.totalPages) {
            _uiState.value = _uiState.value.copy(currentPageIndex = index)
        }
    }
    
    fun toggleTheme() {
        _uiState.value = _uiState.value.copy(isLightTheme = !_uiState.value.isLightTheme)
    }
    
    fun onGetStartedClick() {
        // Navigate to home screen
        onNavigateToHome()
    }
    
    fun onSignInClick() {
        // In a real app, this would trigger navigation to the login flow
        // For now, we'll just navigate to home screen as well
        onNavigateToHome()
    }
}