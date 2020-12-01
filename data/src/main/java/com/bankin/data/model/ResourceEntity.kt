package com.bankin.data.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.bankin.domain.countries.model.Parent

@Entity(tableName = "Resource")
data class ResourceEntity(
    @ColumnInfo(name = "custom")
    val custom: Boolean,
    @PrimaryKey val id: Int,
    @ColumnInfo(name = "is_deleted")
    val is_deleted: Boolean,
    @ColumnInfo(name = "name")
    val name: String,
    @ColumnInfo(name = "other")
    val other: Boolean,
    @ColumnInfo(name = "parent")
    val parent: Parent,
    @ColumnInfo(name = "resource_type")
    val resource_type: String,
    @ColumnInfo(name = "resource_uri")
    val resource_uri: String
)