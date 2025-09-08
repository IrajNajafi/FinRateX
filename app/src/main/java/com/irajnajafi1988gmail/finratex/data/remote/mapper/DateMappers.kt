package com.irajnajafi1988gmail.finratex.data.remote.mapper

import com.irajnajafi1988gmail.finratex.data.remote.model.date.DateDto
import com.irajnajafi1988gmail.finratex.data.remote.model.date.DateResponseDto
import com.irajnajafi1988gmail.finratex.domain.model.DateInfo

// Mapper extension to convert DateDto to DateInfo domain model
fun DateDto.toDomain(): DateInfo = DateInfo(
    year = year,
    monthName = monthName,
    dayOfMonth = dayOfMonth,
    weekDayName = weekDayName
)

// Mapper extension to convert DateResponseDto to DateInfo domain model
fun DateResponseDto.toDomain(): DateInfo = date.toDomain()
