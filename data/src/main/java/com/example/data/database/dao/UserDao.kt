package com.example.data.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.domian.entities.User

@Dao
interface UserDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insertUser(user: User): Long

    @Query("SELECT * FROM User WHERE upper(USERNAME) = upper(:username) limit 1")
    fun isUserExist(username: String?): User?

    @Query("SELECT * FROM User WHERE USERNAME = :user AND PWD = :pwd")
    fun validateUser(user:String?, pwd: String?): User

}