package com.bankin.task.models

import android.os.Parcelable
import com.bankin.data.model.Parent
import kotlinx.android.parcel.Parcelize
import kotlinx.android.parcel.RawValue

@Parcelize
data class ResourceCategoryUiModel(
    val custom: Boolean,
    val id: Int,
    val is_deleted: Boolean,
    val name: String,
    val other: Boolean,
    val parent: Parent,
    val resource_type: String,
    val resource_uri: String
) : Parcelable, BaseUiModel