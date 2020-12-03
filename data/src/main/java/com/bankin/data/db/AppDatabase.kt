package com.bankin.data.db

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.bankin.data.model.Converters
import com.bankin.data.model.ResourceEntity


@Database(entities = [ResourceEntity::class], version = 1, exportSchema = false)
@TypeConverters(Converters::class)
abstract class AppDatabase : RoomDatabase() {
    abstract fun categoriesDao(): ResourceRepoDao
}