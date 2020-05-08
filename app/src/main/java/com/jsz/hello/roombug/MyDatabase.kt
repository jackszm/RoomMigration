package com.jsz.hello.roombug

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(
    entities = [
        User::class,
        Pet::class
    ], version = 1
)
abstract class MyDatabase : RoomDatabase() {
    abstract fun storage(): Storage

    companion object {

        @Volatile
        private var INSTANCE: MyDatabase? = null

        fun getInstance(context: Context): MyDatabase =
            INSTANCE ?: synchronized(this) {
                INSTANCE ?: buildDatabase(context).also { INSTANCE = it }
            }

        private fun buildDatabase(context: Context): MyDatabase {
            return Room.databaseBuilder(
                context.applicationContext,
                MyDatabase::class.java,
                "Sample.db"
            )
                .fallbackToDestructiveMigration()
                .build()
        }
    }
}
