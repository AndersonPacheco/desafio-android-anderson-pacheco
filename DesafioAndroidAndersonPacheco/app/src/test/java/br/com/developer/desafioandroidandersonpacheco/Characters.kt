package br.com.developer.desafioandroidandersonpacheco

import android.util.Log
import br.com.developer.desafioandroidandersonpacheco.data.CharactersService
import br.com.developer.desafioandroidandersonpacheco.data.ServiceGeneric
import br.com.developer.desafioandroidandersonpacheco.util.Constantes
import br.com.developer.desafioandroidandersonpacheco.util.Utils
import org.junit.Test
import java.util.*


class Characters {


    @Test
    fun verificaServico(){

        val calendar: Calendar = Calendar.getInstance()
        var ts = calendar.timeInMillis
        val stringToHash: String = calendar
            .timeInMillis.toString() + Constantes.PRIVATE_KEY + Constantes.PUBLIC_KEY
        var hash = Utils.md5Java(stringToHash)

        var response =  ServiceGeneric.createService(CharactersService::class.java)
            .characters("20",ts.toString(), Constantes.PUBLIC_KEY, hash.toString()).execute()

        when{
            response.code() == 200 -> {
                Log.i("Teste", response.message())
            }
            response.code() == 409 -> {
                Log.i("Teste", response.message())
            }
        }
    }
}