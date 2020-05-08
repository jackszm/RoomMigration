package com.jsz.hello.roombug

import androidx.room.*
import io.reactivex.Completable
import io.reactivex.Single

@Dao
abstract class Storage {
    @Transaction
    open fun updateData(users: List<User>, pets: List<Pet>) {
        deleteAllPets()
        deleteAllUsers()
        insertAll(users)
        insertPets(pets)
    }

    @Query("SELECT * FROM user")
    abstract fun getAll(): Single<List<User>>

    @Insert
    abstract fun insertAll(users: List<User>)

    @Insert
    abstract fun insertPets(users: List<Pet>)

    @Delete
    abstract fun delete(user: User): Completable

    @Query("DELETE FROM user")
    abstract fun deleteAllUsers()

    @Query("DELETE FROM pets")
    abstract fun deleteAllPets(): Completable

}
