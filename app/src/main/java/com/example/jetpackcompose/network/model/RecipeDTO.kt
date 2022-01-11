package com.example.jetpackcompose.network.model

import com.google.gson.annotations.SerializedName

class RecipeDTO(

    @SerializedName("pk")
    var pk: Int? = null,

    var title: String? = null,
    var publisher: String? = null,

    @SerializedName("featured_image")
    var featuredImage: String? = null,
    var rating: Int? = 0,

    @SerializedName("source_url")
    var sourceUrl: String? = null,
    var description: String? = null,

    @SerializedName("cooking_instructions")
    var cookingInstructions: String? = null,
    var ingredients: List<String>? = listOf(),

    @SerializedName("date_added")
    var dateAdded: String? = null,

    @SerializedName("date_updated")
    var dateUpdated: String? = null,
)