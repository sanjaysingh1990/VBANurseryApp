package org.example.project.domain

interface SplashRepository {
    suspend fun shouldShowOnboarding(): Boolean
}