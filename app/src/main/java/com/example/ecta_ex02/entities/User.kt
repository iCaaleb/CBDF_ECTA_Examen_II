package com.example.ecta_ex02.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "user_table")
data class User (
    @PrimaryKey(autoGenerate = true) val id: Long = 0,
    val name: String = "",
    val username: String = "",
    val email: String = ""
)