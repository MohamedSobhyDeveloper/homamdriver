package com.otex.homamrestaurant.view.login.model

data class ModelLoginRestaurant(
    val expires_in: String,
    val restaurant: Restaurant,
    val token: String,
    val token_type: String
)