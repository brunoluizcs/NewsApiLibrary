package br.com.fiap.mob18.newsapilibrary.model

import com.google.gson.annotations.SerializedName

data class Source(
    @SerializedName("id") val id : String,
    @SerializedName("name") val name: String
)