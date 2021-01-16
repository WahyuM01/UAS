package com.fitrianti.inibolaapp.data.remote.response

import com.google.gson.annotations.SerializedName
import com.fitrianti.inibolaapp.data.model.EventWithImage

data class EventsRes(
    @SerializedName("events")
    val events: List<EventWithImage>?
)