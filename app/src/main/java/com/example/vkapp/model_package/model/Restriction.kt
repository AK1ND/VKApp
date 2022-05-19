package com.example.vkapp.model_package.model

data class Restriction(
    val always_shown: Int,
    val blur: Int,
    val can_play: Int,
    val can_preview: Int,
    val card_icon: List<CardIcon>,
    val disclaimer_type: Int,
    val list_icon: List<Icon>,
    val text: String,
    val title: String
)