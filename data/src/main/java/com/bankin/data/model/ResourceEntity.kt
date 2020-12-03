package com.bankin.data.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Ignore
import androidx.room.PrimaryKey
import com.bankin.domain.countries.model.Parent

@Entity(tableName = "ResourceEntity")
data class ResourceEntity(
    @PrimaryKey val id: Int?,
    @ColumnInfo(name = "resource_uri")
    val resource_uri: String?,
    @ColumnInfo(name = "resource_type")
    val resource_type: String?,
    @ColumnInfo(name = "name")
    val name: String?,
    @ColumnInfo(name = "parent")
    val parent: Parent?,
    @ColumnInfo(name = "custom")
    val custom: Boolean?,
    @ColumnInfo(name = "other")
    val other: Boolean?,
    @ColumnInfo(name = "is_deleted")
    val is_deleted: Boolean?





)