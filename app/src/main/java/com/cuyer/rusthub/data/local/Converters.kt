package com.cuyer.rusthub.data.local

import androidx.room.ProvidedTypeConverter
import androidx.room.TypeConverter
import com.cuyer.rusthub.data.remote.dto.items.Ingredient
import com.cuyer.rusthub.data.remote.dto.items.ScrappedComponents
import com.cuyer.rusthub.data.util.JsonParser
import com.google.gson.reflect.TypeToken

@ProvidedTypeConverter
class Converters(
    private val jsonParser: JsonParser
) {
    @TypeConverter
    fun fromIngredientsJson(json: String): List<Ingredient> {
        return jsonParser.fromJson<ArrayList<Ingredient>>(
            json,
            object : TypeToken<ArrayList<Ingredient>>(){}.type
        ) ?: emptyList()
    }

    @TypeConverter
    fun toIngredientsJson(ingredients: List<Ingredient>): String {
        return jsonParser.toJson(
            ingredients,
            object : TypeToken<ArrayList<Ingredient>>(){}.type
        ) ?: "[]"
    }

    @TypeConverter
    fun fromScrappedComponentsJson(json: String): List<ScrappedComponents> {
        return jsonParser.fromJson<ArrayList<ScrappedComponents>>(
            json,
            object : TypeToken<ArrayList<ScrappedComponents>>(){}.type
        ) ?: emptyList()
    }

    @TypeConverter
    fun toScrappedComponentsJson(scrappedComponents: List<ScrappedComponents>): String {
        return jsonParser.toJson(
            scrappedComponents,
            object : TypeToken<ArrayList<ScrappedComponents>>(){}.type
        ) ?: "[]"
    }
}