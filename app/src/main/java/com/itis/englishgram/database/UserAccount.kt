package com.itis.englishgram.database

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName="users_database")
data class UserAccount(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    val username: String,
    val word: String
)