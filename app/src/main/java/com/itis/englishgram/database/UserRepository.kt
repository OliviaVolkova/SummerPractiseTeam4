package com.itis.englishgram.database

import androidx.lifecycle.LiveData

class UserRepository(private val userDao: UserDao) {

    val readAllData: LiveData<List<UserAccount>> = userDao.readAllData()

    suspend fun addUser(user: UserAccount){
        userDao.addUser(user)
    }

}