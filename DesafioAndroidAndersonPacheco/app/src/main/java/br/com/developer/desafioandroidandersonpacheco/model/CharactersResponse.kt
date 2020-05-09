package br.com.developer.desafioandroidandersonpacheco.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize
import kotlinx.android.parcel.RawValue

@Parcelize
data class CharactersResponse<T> (
    @SerializedName("Data") var data: @RawValue T? = null,
    @SerializedName("ResultCode") var resultCode: Int? = null,
    @SerializedName("Message") var message: String? = null
) : Parcelable