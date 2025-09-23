package org.example.project.domain

import org.example.project.domain.model.OnboardingPage

interface OnboardingRepository {
    fun getOnboardingPages(): List<OnboardingPage>
}