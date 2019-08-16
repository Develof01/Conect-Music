package com.example.domian.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable

@Entity(tableName = "User")
data class User(

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = ID_USER)
    var idUser: Long?,
    @ColumnInfo(name = USERNAME)
    var username: String?,
    @ColumnInfo(name = PWD)
    var pwd: String?,
    @ColumnInfo(name = EMAIL)
    var email: String?

): Serializable {

    constructor() : this(null, "", "", "")

    companion object {
        const val ID_USER = "ID"
        const val USERNAME = "USERNAME"
        const val PWD = "PWD"
        const val EMAIL = "EMAIL"
    }

}
