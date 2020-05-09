package br.com.developer.desafioandroidandersonpacheco.model.characters

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class CharactersResults (
    @SerializedName("id") var id: Int?,
    @SerializedName("name") var name: String?,
    @SerializedName("description") var description: String?,
    @SerializedName("modified") var modified: String?,
    @SerializedName("thumbnail") var thumbnail: Thumbnail?,
    @SerializedName("resourceURI") var resourceURI: String?
): Parcelable