package com.bankin.task.models

import android.os.Parcelable
import com.bankin.data.model.ParentEntity
import kotlinx.android.parcel.Parcelize

@Parcelize
data class ResourceUiModel(
    val custom: Boolean,
    val id: Int,
    val is_deleted: Boolean,
    val name: String,
    val other: Boolean,
    val parent: ParentEntity,
    val resource_type: String,
    val resource_uri: String,
    var expand: Boolean = false
) : Parcelable, BaseUiModel