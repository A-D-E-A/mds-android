package fr.mds.helloworld.data.models

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class XkcdComic(
        @SerialName(value = "num")
        val id: Int,
        val month: String,
        val year: String,
        @SerialName(value = "safe_title")
        val safeTitle: String,
        val alt: String,
        val img: String,
        val title: String,
        val day: String,
)