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
        deleteAllUsers()
        insertUsers(users)
    }

    @Query("DELETE FROM user")
    abstract fun deleteAllUsers()

    @Insert
    abstract fun insertUsers(users: List<User>)

    @Query("SELECT * FROM user")
    abstract fun getAll(): Single<List<User>>
}
