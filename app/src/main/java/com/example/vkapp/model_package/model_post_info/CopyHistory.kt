package com.example.vkapp.model_package.model_post_info

data class CopyHistory(
    val attachments: List<Attachment>,
    val date: Int,
    val from_id: Int,
    val id: Int,
    val owner_id: Int,
    val post_source: PostSource,
    val post_type: String,
    val text: String
)