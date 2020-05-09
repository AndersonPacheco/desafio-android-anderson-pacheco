package br.com.developer.desafioandroidandersonpacheco.model.characters

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Characters (
    @SerializedName("code") val code: Int?,
    @SerializedName("status") var status: String?,
    @SerializedName("data") var data: CharactersData?
) : Parcelable