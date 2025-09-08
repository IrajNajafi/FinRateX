package com.irajnajafi1988gmail.finratex.data.remote.model.date

import com.google.gson.annotations.SerializedName

data class DateDto (

    @SerializedName("Y") val year: String,
    @SerializedName("F") val monthName: String,
    @SerializedName("j") val dayOfMonth: String,
    @SerializedName("l") val weekDayName: String,

)
