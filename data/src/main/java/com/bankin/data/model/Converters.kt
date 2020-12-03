package com.bankin.data.model

import androidx.room.TypeConverter
import com.bankin.domain.countries.model.Parent
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class Converters {

    @TypeConverter
    fun fromMediaToJson(stat: Parent): String {
        return Gson().toJson(stat)
    }

    /**
     * Convert a json to a list of Images
     */
    @TypeConverter
    fun fromJsonToMedia(jsonImages: String): Parent {
        val type = object : TypeToken<Parent>() {}.type
        return Gson().fromJson<Parent>(jsonImages, type)
    }

}