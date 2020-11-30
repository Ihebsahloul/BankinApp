package com.bankin.data.response

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.bankin.data.model.Parent

data class ResourceResponse(

    val custom: Boolean,
    val id: Int,
    val is_deleted: Boolean,
    val name: String,
    val other: Boolean,
    val parent: Parent,
    val resource_type: String,
    val resource_uri: String
)