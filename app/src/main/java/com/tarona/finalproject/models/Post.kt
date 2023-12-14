package com.tarona.finalproject.models

import com.google.gson.annotations.SerializedName

data class Post(
    @SerializedName("id")
    val id: String,
    @SerializedName("name")
    val name: String,
    @SerializedName("description")
    val description: String,
)