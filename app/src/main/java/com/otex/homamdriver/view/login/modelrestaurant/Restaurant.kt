package com.otex.homamrestaurant.view.login.model

data class Restaurant(
    val address: String,
    val created_at: String,
    val description: Any,
    val district_id: Int,
    val email: String,
    val id: Int,
    val image: String,
    val last_order: Any,
    val logo: String,
    val name: String,
    val phone: String,
    val status: Int,
    val updated_at: String
)