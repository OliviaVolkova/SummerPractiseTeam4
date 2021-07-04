package com.itis.englishgram.database

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class UserViewModel(application: Application) : AndroidViewModel(application) {

    private val readAllData: LiveData<List<UserAccount>>
    private val repository:UserRepository
    init {
        val userDao= AccountDatabase.getDatabase(application).userDao()
        repository= UserRepository(userDao)

        readAllData=repository.readAllData
    }

    fun addUser(user: UserAccount){
        viewModelScope.launch(Dispatchers.IO) {
            repository.addUser(user)
        }
    }
}