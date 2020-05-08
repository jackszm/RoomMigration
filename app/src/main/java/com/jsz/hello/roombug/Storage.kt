package com.jsz.hello.roombug

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Transaction
import io.reactivex.Single

@Dao
abstract class Storage {
    @Transaction
    open fun updateData(users: List<User>, pets: List<Pet>) {
        deleteAllPets()
        deleteAllUsers()
        insertUsers(users)
        insertPets(pets)
    }

    @Query("DELETE FROM Pet")
    abstract fun deleteAllPets()

    @Query("DELETE FROM user")
    abstract fun deleteAllUsers()

    @Insert
    abstract fun insertUsers(users: List<User>)

    @Insert
    abstract fun insertPets(users: List<Pet>)

    @Query("SELECT * FROM user")
    abstract fun getAll(): Single<List<User>>
}
