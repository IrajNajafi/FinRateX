package com.irajnajafi1988gmail.finratex.data.remote.model.date

import com.google.gson.annotations.SerializedName

data class DateResponseDto (

    @SerializedName("success")
    val success: Int,

    @SerializedName("message")
    val message: String,

    @SerializedName("help")
    val help: String,

    @SerializedName("date")
    val date: DateDto
)
