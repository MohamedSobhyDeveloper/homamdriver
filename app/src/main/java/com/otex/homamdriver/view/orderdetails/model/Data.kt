package com.otex.homamdriver.view.orderdetails.model

data class Data(
    val address: String,
    val code: String,
    val date: String,
    val driver: String,
    val email: String,
    val id: String,
    val items: List<Item>,
    val lat: String,
    val long: String,
    val name: String,
    val note: String,
    val phone: Any,
    val restaurant: String,
    val restaurant_logo: String,
    val shipping_fees: String,
    val status: String,
    val total: String
)