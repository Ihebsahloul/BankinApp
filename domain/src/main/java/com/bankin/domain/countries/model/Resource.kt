package com.bankin.domain.countries.model


data class Resource(


    val id: Int?,
    val resource_uri: String?,
    val resource_type: String?,
    val name: String?,
    val parent: Parent?,
    val custom: Boolean?,
    val other: Boolean?,
    val is_deleted: Boolean?,
    var expand: Boolean = false
)