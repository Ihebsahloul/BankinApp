package com.bankin.task.models

import android.os.Parcelable
import com.bankin.data.model.ParentEntity
import com.bankin.domain.countries.model.Parent
import kotlinx.android.parcel.Parcelize

@Parcelize
data class ResourceUiModel(
        val id: Int?,
        val resource_uri: String?,
        val resource_type: String?,
        val name: String?,
        val parent: Parent?,
        val custom: Boolean?,
        val other: Boolean?,
        val is_deleted: Boolean?,
        var expand: Boolean = false
) : Parcelable, BaseUiModel