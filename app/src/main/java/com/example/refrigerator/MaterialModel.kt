package com.example.refrigerator
import com.google.gson.annotations.SerializedName
data class MaterialModel (
    @SerializedName("id") val id: Int,
    @SerializedName("material_id") var material_id: String,
    @SerializedName("name") var name: String,
    @SerializedName("image") var image: String
)

