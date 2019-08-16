package com.example.domian.entities

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.ForeignKey.CASCADE


@Entity(
    tableName = "UserFavoriteTracks",
    foreignKeys = [ForeignKey(
        entity = User::class,
        parentColumns = arrayOf("uid"),
        childColumns = arrayOf("ownerId"),
        onDelete = CASCADE
    )]
)
class UserFavoriteTracks {
}