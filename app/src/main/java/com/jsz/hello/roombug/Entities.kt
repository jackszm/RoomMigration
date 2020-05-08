package com.jsz.hello.roombug

import androidx.room.*

@Entity
data class User(
    @PrimaryKey val userId: String,
    @ColumnInfo(name = "first_name") val firstName: String?,
    @ColumnInfo(name = "last_name") val lastName: String?
)

@Entity(
    tableName = "pets",
    foreignKeys = [ForeignKey(
        entity = User::class,
        parentColumns = arrayOf("userId"),
        childColumns = arrayOf("owner")
    )]
)
data class Pet(
    @PrimaryKey val petId: String,
    val name: String,
    val owner: String
)

//class UserAndAllPets {
//    @Embedded
//    var user: User? = null
//
//    @Relation(parentColumn = "userId", entityColumn = "owner")
//    var pets: List<Pet> = ArrayList()
//}
