package org.example.project.domain

abstract class UseCase<in P, out R> {
    suspend operator fun invoke(parameters: P): Result<R> {
        return try {
            Result.success(execute(parameters))
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
    
    protected abstract suspend fun execute(parameters: P): R
}

abstract class NoParamUseCase<out R> {
    suspend operator fun invoke(): Result<R> {
        return try {
            Result.success(execute())
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
    
    protected abstract suspend fun execute(): R
}