package com.bankin.domain.countries.model


data class Resource(

    val custom: Boolean,
    val id: Int,
    val is_deleted: Boolean,
    val name: String,
    val other: Boolean,
    val parent: Parent,
    val resource_type: String,
    val resource_uri: String,
    var expand: Boolean = false
)