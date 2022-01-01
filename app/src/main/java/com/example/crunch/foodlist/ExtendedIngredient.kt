package com.example.crunch.foodlist


import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class ExtendedIngredient(
    val amount: Double,
    @SerializedName("consistency")
    val consistency: String,
    @SerializedName("id")
    val id: Int,
    @SerializedName("image")
    val image: String,
    @SerializedName("meta")
    val meta: List<String>,
    @SerializedName("metaInformation")
    val metaInformation: List<String>,
    @SerializedName("name")
    val name: String,
    @SerializedName("original")
    val original: String,
    @SerializedName("unit")
    val unit: String
): Parcelable