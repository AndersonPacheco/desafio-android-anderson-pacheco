package br.com.developer.desafioandroidandersonpacheco.model.comics

import android.os.Parcelable
import br.com.developer.desafioandroidandersonpacheco.model.characters.Thumbnail
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class ComicsResults (
    @SerializedName("id") var id: Int?,
    @SerializedName("digitalId") var digitalId: Int?,
    @SerializedName("title") var title: String?,
    @SerializedName("issueNumber") var issueNumber: Int?,
    @SerializedName("variantDescription") var variantDescription: String?,
    @SerializedName("description") var description: String?,
    @SerializedName("modified") var modified: String?,
    @SerializedName("prices") var prices: ArrayList<Prices>?,
    @SerializedName("thumbnail") var thumbnail: Thumbnail?
): Parcelable