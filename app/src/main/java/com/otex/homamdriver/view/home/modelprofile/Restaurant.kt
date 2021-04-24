package com.otex.homamdriver.view.home.modelprofile

data class Restaurant(
    val address: String,
    val created_at: String,
    val description: String,
    val district_id: Int,
    val email: String,
    val id: Int,
    val image: String,
    val is_open: Int,
    val last_order: Any,
    val lat: String,
    val logo: String,
    val long: String,
    val name: String,
    val order_no: Any,
    val phone: String,
    val status: Int,
    val updated_at: String
)