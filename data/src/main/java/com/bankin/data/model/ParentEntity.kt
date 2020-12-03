package com.bankin.data.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable

@Entity(tableName = "Parent")
data class ParentEntity(
    @PrimaryKey val id: Int,
    @ColumnInfo(name = "resource_uri")
    val resource_uri: String,
    @ColumnInfo(name = "resource_type")
    val resource_type: String

)