package com.cuyer.rusthub.data.local

import androidx.room.ProvidedTypeConverter
import androidx.room.TypeConverter
import com.cuyer.rusthub.data.remote.dto.items.Ingredient
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
}