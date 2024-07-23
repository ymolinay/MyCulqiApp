package com.heyrex.myculqiapp.core.utils

fun String.formatEmailUsername(): String {
    return this.split("@")[0].split(".").joinToString(" ")
}

fun String.isValidEmail(): Boolean {
    val emailRegex = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,6}$"
    return this.matches(emailRegex.toRegex())
}