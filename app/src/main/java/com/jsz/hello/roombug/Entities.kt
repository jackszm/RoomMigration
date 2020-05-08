package com.jsz.hello.roombug

import androidx.room.*

@Entity
data class User(
    @PrimaryKey val userId: String,
    @ColumnInfo(name = "first_name") val firstName: String?,
    @ColumnInfo(name = "last_name") val lastName: String?
)

@Entity(
    tableName = "Pet",
    foreignKeys = [ForeignKey(
        entity = User::class,
        parentColumns = [("userId")],
        childColumns = [("owner")]
    )],
    indices = [Index(value = ["owner"])]
)
data class Pet(
    @PrimaryKey val petId: String,
    val name: String,
    val owner: String
)
