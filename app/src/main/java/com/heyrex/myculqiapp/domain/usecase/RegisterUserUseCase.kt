package com.heyrex.myculqiapp.domain.usecase

import com.heyrex.myculqiapp.domain.model.UserRegister
import com.heyrex.myculqiapp.domain.repository.SessionRepository
import javax.inject.Inject

class RegisterUserUseCase
@Inject constructor(private val sessionRepository: SessionRepository) {
    fun execute(user: UserRegister) = sessionRepository.register(user)
}