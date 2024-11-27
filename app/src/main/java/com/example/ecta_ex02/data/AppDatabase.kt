package com.example.ecta_ex02.data

import android.content.Context
import androidx.room.Database
import com.example.ecta_ex02.entities.User

@Database(entities = [User::class], version = 1)
abstract class AppDatabase {
    abstract fun userDao(): UserDao
}