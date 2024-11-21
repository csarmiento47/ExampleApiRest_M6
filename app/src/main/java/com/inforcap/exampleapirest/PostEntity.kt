package com.inforcap.exampleapirest

import com.google.gson.annotations.SerializedName

data class PostEntity(
    @SerializedName("user_id")
    val userId: Int,
    val id: Int,
    val title: String,
    val body: String
)
