package com.fitrianti.inibolaapp.data.remote.response

import com.google.gson.annotations.SerializedName
import com.fitrianti.inibolaapp.data.model.EventWithImage

data class SearchEventsRes(
    @SerializedName("event")
    val events: List<EventWithImage>
)