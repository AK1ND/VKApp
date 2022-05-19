package com.example.vkapp.model_package.model

data class Audio(
    val artist: String,
    val date: Int,
    val duration: Int,
    val id: Int,
    val is_explicit: Boolean,
    val is_focus_track: Boolean,
    val main_artists: List<MainArtist>,
    val owner_id: Int,
    val short_videos_allowed: Boolean,
    val stories_allowed: Boolean,
    val stories_cover_allowed: Boolean,
    val title: String,
    val track_code: String,
    val url: String
)