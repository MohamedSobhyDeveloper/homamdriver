package com.otex.homamdriver.view.login.modelpassword

data class ModelUpdatePassword(
    val expires_in: String,
    val message: String,
    val restaurant: Restaurant,
    val status: String,
    val token: String,
    val token_type: String
)