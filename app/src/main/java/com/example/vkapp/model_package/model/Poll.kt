package com.example.vkapp.model_package.model

data class Poll(
    val anonymous: Boolean,
    val answer_ids: List<Any>,
    val answers: List<Answer>,
    val author_id: Int,
    val can_edit: Boolean,
    val can_report: Boolean,
    val can_share: Boolean,
    val can_vote: Boolean,
    val closed: Boolean,
    val created: Int,
    val disable_unvote: Boolean,
    val embed_hash: String,
    val end_date: Int,
    val friends: List<Friend>,
    val id: Int,
    val is_board: Boolean,
    val multiple: Boolean,
    val owner_id: Int,
    val photo: PhotoXX,
    val question: String,
    val votes: Int
)