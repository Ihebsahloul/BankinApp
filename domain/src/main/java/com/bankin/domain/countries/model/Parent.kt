package com.bankin.domain.countries.model

import java.io.Serializable

data class Parent(
    val id: Int,
    val resource_type: String,
    val resource_uri: String
) : Serializable