package com.example.ecta_ex02.repositories

import com.example.ecta_ex02.entities.CommentEntity
import com.example.ecta_ex02.entities.PostEntity
import com.example.ecta_ex02.entities.UserEntity
import com.example.ecta_ex02.network.UserRetrofit
import com.example.ecta_ex02.services.UserService

class UserRepository(private val userService: UserService = UserRetrofit.getInstanciaRetrofit) {

    suspend fun getAllUsers(): List<UserEntity> {
        return userService.getAllUsers()
    }

    suspend fun getPostsByUserId(id: Long): List<PostEntity> {
        return userService.getPostsByUserId(id)
    }

    suspend fun getCommentsByPostId(id: Long): List<CommentEntity> {
        return userService.getCommentsByPostId(id)
    }
}