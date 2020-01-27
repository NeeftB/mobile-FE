package com.example.checkerapp.model

import com.google.gson.annotations.SerializedName


data class LoggedInUser(
    @SerializedName("passId")
    val passId: String,

    @SerializedName("password")
    val password: String
)
