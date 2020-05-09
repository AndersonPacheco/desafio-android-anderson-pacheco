package br.com.developer.desafioandroidandersonpacheco.data

import br.com.developer.desafioandroidandersonpacheco.model.characters.Characters
import br.com.developer.desafioandroidandersonpacheco.model.characters.CharactersData
import br.com.developer.desafioandroidandersonpacheco.model.comics.Comics
import br.com.developer.desafioandroidandersonpacheco.model.comics.DataComics
import retrofit2.Call
import retrofit2.http.*

interface CharactersService {

    @Headers("Content-Type: application/json")
    @GET("v1/public/characters?")
    fun characters(
        @Query("limit") limit: String,
        @Query ( "ts") ts: String,
        @Query ( "apikey") apikey: String,
        @Query ( "hash") hash: String

    ): Call<Characters>

    @Headers("Content-Type: application/json")
    @GET("v1/public/characters/{id}/comics?")
    fun comics(
        @Path("id") id: String,
        @Query ( "ts") ts: String,
        @Query ( "apikey") apikey: String,
        @Query ( "hash") hash: String
    ): Call<Comics>
}
