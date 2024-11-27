package com.example.ecta_ex02.entities

data class CommentEntity (
    val postId: Long = 0,
    val id: Long = 0,
    val name: String = "",
    val email: String = "",
    val body: String = ""
)