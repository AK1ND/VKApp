package com.example.vkapp.model_package.model_user_photo_and_name

data class Response(
    val can_access_closed: Boolean,
    val first_name: String,
    val id: Int,
    val is_closed: Boolean,
    val last_name: String,
    val photo_100: String
)