package org.example.project.presentation

import org.example.project.domain.model.OnboardingPage

data class OnboardingUiState(
    val pages: List<OnboardingPage> = emptyList(),
    val currentPageIndex: Int = 0,
    val isLoading: Boolean = true,
    val isLightTheme: Boolean = true
) {
    val currentPage: OnboardingPage?
        get() = pages.getOrNull(currentPageIndex)
    
    val totalPages: Int
        get() = pages.size
}