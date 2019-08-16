package com.example.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.data.database.dao.TrackDao
import com.example.data.database.dao.UserDao
import com.example.domian.entities.TrackInformation
import com.example.domian.entities.User


@Database(entities = [User::class, TrackInformation::class], version = 1)
abstract class AppDatabase: RoomDatabase() {
    abstract fun userDao(): UserDao
    abstract fun trackDao(): TrackDao
}