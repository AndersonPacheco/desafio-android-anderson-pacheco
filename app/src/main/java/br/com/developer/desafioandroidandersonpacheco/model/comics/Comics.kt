package br.com.developer.desafioandroidandersonpacheco.model.comics

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Comics (
    @SerializedName("code") val code: Int?,
    @SerializedName("status") var status: String?,
    @SerializedName("data") var data: DataComics?
) : Parcelable