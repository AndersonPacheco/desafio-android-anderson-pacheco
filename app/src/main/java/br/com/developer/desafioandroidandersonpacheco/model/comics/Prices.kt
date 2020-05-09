package br.com.developer.desafioandroidandersonpacheco.model.comics

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Prices (
    @SerializedName("type") var type: String?,
    @SerializedName("price") var price: Double?
): Parcelable