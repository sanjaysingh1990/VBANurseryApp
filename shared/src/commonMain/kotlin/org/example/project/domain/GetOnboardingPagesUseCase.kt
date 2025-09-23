package org.example.project.domain

import org.example.project.domain.model.OnboardingPage

class GetOnboardingPagesUseCase(
    private val repository: OnboardingRepository
) : UseCase<Unit, List<OnboardingPage>>() {
    
    override suspend fun execute(parameters: Unit): List<OnboardingPage> {
        return repository.getOnboardingPages()
    }
}