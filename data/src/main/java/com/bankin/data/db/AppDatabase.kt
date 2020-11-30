package com.bankin.data.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.bankin.data.model.ResourceEntity


@Database(entities = [ResourceEntity::class], version = 1, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {
    abstract fun trendingRepoDao(): ResourceRepoDao
}