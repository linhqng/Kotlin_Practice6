package com.example.practicee6practiceee


import com.google.gson.annotations.SerializedName

data class Image(
    @SerializedName("description")
    val description: String,
    @SerializedName("name")
    val name: String
)