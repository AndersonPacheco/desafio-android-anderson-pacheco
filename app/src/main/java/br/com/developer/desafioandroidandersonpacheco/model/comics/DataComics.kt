package br.com.developer.desafioandroidandersonpacheco.model.comics

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class DataComics (
    @SerializedName("offset") var offset: Int?,
    @SerializedName("limit") var limit: Int?,
    @SerializedName("total") var total: Int?,
    @SerializedName("count") var count: Int?,
    @SerializedName("results") var results: ArrayList<ComicsResults>?
): Parcelable