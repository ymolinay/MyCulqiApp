package com.heyrex.myculqiapp.domain.usecase

import com.heyrex.myculqiapp.domain.repository.SessionRepository
import javax.inject.Inject

class GetUserProfileUseCase
@Inject constructor(private val sessionRepository: SessionRepository) {
    fun execute(id: String) = sessionRepository.profile(id)
}