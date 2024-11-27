package com.example.ecta_ex02.data

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.ecta_ex02.entities.User

@Dao
interface UserDao {
    @Insert
    suspend fun insert(user: User): Long

    @Query("SELECT * FROM user_table")
    suspend fun getAllUsers(): List<User>

    @Query("DELETE FROM user_table WHERE id = :userId")
    suspend fun deleteUserById(userId: Int)

    @Delete
    suspend fun deleteUser(user: User)
}