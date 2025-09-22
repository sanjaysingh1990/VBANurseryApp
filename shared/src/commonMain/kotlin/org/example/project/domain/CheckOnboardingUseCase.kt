package org.example.project.domain

class CheckOnboardingUseCase(
    private val repository: SplashRepository
) : NoParamUseCase<Boolean>() {
    
    override suspend fun execute(): Boolean {
        return repository.shouldShowOnboarding()
    }
}