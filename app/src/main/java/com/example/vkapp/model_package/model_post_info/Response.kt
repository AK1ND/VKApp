package com.example.vkapp.model_package.model_post_info

data class Response(
    val can_archive: Boolean,
    val can_delete: Int,
    val can_pin: Int,
    val comments: Comments,
    val copy_history: List<CopyHistory>,
    val date: Int,
    val donut: Donut,
    val from_id: Int,
    val hash: String,
    val id: Int,
    val is_archived: Boolean,
    val is_favorite: Boolean,
    val likes: Likes,
    val owner_id: Int,
    val post_source: PostSourceX,
    val post_type: String,
    val reposts: Reposts,
    val short_text_rate: Double,
    val text: String,
    val views: Views,
    val attachments: List<Attachment>
)