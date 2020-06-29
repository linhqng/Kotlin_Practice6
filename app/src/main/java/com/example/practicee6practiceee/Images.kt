package com.example.practicee6practiceee


import com.google.gson.annotations.SerializedName

data class Images(
    @SerializedName("images")
    val images: List<Image>,
    @SerializedName("name")
    val name: String
)