package com.example.checkerapp.model

import com.google.gson.annotations.SerializedName

data class StatusHistory(

    @SerializedName("status")
    val status: String,

    @SerializedName("lastCheckInDate")
    val lastCheckedInDate: String,

    @SerializedName("lastCheckOutDate")
    val lastCheckedOutDate: String,

    @SerializedName("reason")
    val reason: String

)