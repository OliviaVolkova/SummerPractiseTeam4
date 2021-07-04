package com.itis.englishgram.database

import android.content.Context
import android.provider.CalendarContract
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities =[UserAccount::class],version = 1, exportSchema = false)
abstract class AccountDatabase: RoomDatabase() {

    abstract fun userDao(): UserDao

    companion object{
        @Volatile
        private var INSTANCE: AccountDatabase?=null

        fun getDatabase(context: Context): AccountDatabase{
            val tempInstance= INSTANCE
            if (tempInstance!=null){
                return tempInstance
            }
            synchronized(this){
                val instance= Room.databaseBuilder(
                    context.applicationContext,
                    AccountDatabase::class.java,
                    "users_database"
                ).build()
                INSTANCE=instance
                return instance
            }
        }
    }
}