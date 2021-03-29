package com.otex.homamdriver.view.login.model

data class ModelLogin(
    val driver: Driver,
    val expires_in: String,
    val token: String,
    val token_type: String
)