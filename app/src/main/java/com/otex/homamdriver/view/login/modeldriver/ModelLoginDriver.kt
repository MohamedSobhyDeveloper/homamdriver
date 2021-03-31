package com.otex.homamdriver.view.login.modeldriver

data class ModelLoginDriver(
    val driver: Driver,
    val expires_in: String,
    val token: String,
    val token_type: String
)