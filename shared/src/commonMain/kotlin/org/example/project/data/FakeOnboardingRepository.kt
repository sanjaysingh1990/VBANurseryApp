package org.example.project.data

import org.example.project.domain.OnboardingRepository
import org.example.project.domain.model.OnboardingPage

class FakeOnboardingRepository : OnboardingRepository {
    override fun getOnboardingPages(): List<OnboardingPage> {
        // In a real app, this would come from a data source
        return listOf(
            OnboardingPage(
                title = "Find Your Green Oasis",
                subtitle = "Buy plants, learn care tips, and join our vibrant community."
            ),
            OnboardingPage(
                title = "Discover Rare Plants",
                subtitle = "Explore our collection of rare and exotic plants from around the world."
            ),
            OnboardingPage(
                title = "Expert Care Guides",
                subtitle = "Get detailed care instructions for each plant in your collection."
            )
        )
    }
}