package com.example.checkerapp.model

import com.google.gson.annotations.SerializedName

data class StatusChange(

    @SerializedName("reason")
    val reason: String

)