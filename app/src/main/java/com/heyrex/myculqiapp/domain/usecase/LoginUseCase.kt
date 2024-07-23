package com.heyrex.myculqiapp.domain.usecase

import com.heyrex.myculqiapp.domain.model.Login
import com.heyrex.myculqiapp.domain.repository.SessionRepository
import javax.inject.Inject

class LoginUseCase
@Inject constructor(private val sessionRepository: SessionRepository) {
    fun execute(login: Login) = sessionRepository.login(login)
}