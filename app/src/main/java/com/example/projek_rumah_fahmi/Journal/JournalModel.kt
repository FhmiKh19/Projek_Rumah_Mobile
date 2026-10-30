package com.example.projek_rumah_fahmi.Journal

data class JournalModel(
    val moodEmoji: String,
    val moodName: String,
    val moodBgColor: String,
    val moodTextColor: String,
    val timeAgo: String,
    val note: String,
    val tag1: String,
    val tag2: String,
    val tag3: String? = null
)
