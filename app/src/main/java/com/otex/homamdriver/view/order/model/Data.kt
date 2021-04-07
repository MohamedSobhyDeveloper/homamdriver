package com.otex.homamdriver.view.order.model

data class Data(
    val code: String,
    val date: String,
    val dishes: Int,
    val id: Int,
    val restaurant: String,
    val restaurant_logo: String,
    val status: String,
    val total: Int
)