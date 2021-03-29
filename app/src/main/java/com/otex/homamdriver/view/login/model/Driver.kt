package com.otex.homamdriver.view.login.model

data class Driver(
    val created_at: String,
    val email: String,
    val have_order: Int,
    val id: Int,
    val image: String,
    val name: String,
    val phone: String,
    val status: Int,
    val updated_at: String
)