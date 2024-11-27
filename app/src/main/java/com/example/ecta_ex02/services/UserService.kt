package com.example.ecta_ex02.services

import com.example.ecta_ex02.entities.CommentEntity
import com.example.ecta_ex02.entities.PostEntity
import com.example.ecta_ex02.entities.UserEntity
import retrofit2.http.GET
import retrofit2.http.Path

interface UserService {

    @GET("users")
    suspend fun getAllUsers(): List<UserEntity>

    @GET("users/{id}/posts")
    suspend fun getPostsByUserId(@Path("id") id: Long): List<PostEntity>

    @GET("posts/{id}/comments")
    suspend fun getCommentsByPostId(@Path("id") id: Long): List<CommentEntity>
}