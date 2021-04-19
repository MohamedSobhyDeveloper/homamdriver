package com.otex.homamdriver.view.orderdetails.modelaccept

data class Order(
    val address: String,
    val code: String,
    val date: String,
    val discount: Int,
    val driver: String,
    val email: String,
    val id: Int,
    val items: List<Item>,
    val lat: String,
    val long: String,
    val name: String,
    val note: String,
    val order_status: String,
    val order_status_str: String,
    val phone: String,
    val restaurant: String,
    val restaurant_logo: String,
    val shipping_fees: Int,
    val status: String,
    val total: Int
)