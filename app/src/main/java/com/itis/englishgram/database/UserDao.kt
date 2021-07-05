package com.itis.englishgram.database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query


@Dao
interface UserDao {

    @Insert(onConflict = OnConflictStrategy.ABORT)
     fun addUser(userAccount: UserAccount)

    @Query("SELECT * FROM users_database ORDER BY id ASC")
     fun readAllData(): LiveData<List<UserAccount>>

}