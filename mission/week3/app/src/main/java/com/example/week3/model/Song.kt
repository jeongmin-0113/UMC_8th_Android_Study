package com.example.week3.model

data class Song(
    val title: String = "",
    val singer: String = "",
    val cover: Int = -1,
    val second: Int = 0,
    val playTime: Int = 0,
    var isPlaying: Boolean = false
)
