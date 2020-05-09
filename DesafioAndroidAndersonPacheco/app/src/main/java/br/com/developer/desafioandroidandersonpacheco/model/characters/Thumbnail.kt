package br.com.developer.desafioandroidandersonpacheco.model.characters

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Thumbnail (
    @SerializedName("path") var path: String?,
    @SerializedName("extension") var extension: String?
): Parcelable