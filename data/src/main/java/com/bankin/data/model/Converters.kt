package com.bankin.data.model

import androidx.room.TypeConverter
import com.bankin.domain.countries.model.Parent
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class Converters {

    @TypeConverter
    fun fromParentToJson(parent: Parent?): String? {
        return Gson().toJson(parent)
    }

    /**
     * Convert a json to a parent
     */
    @TypeConverter
    fun fromJsonToParent(parentJson: String?): Parent {
        val type = object : TypeToken<Parent>() {}.type
        return Gson().fromJson<Parent>(parentJson, type)
    }

}