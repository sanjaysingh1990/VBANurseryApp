package org.example.project.data

import org.example.project.domain.SplashRepository

class FakeSplashRepository : SplashRepository {
    override suspend fun shouldShowOnboarding(): Boolean {
        // In a real app, this would check preferences or other conditions
        // For now, we'll return true to navigate to onboarding screen
        return true
    }
}