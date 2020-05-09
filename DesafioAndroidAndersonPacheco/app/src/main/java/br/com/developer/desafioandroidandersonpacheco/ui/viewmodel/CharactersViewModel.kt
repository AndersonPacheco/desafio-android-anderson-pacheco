package br.com.developer.desafioandroidandersonpacheco.ui.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import br.com.developer.desafioandroidandersonpacheco.R
import br.com.developer.desafioandroidandersonpacheco.data.CharactersService
import br.com.developer.desafioandroidandersonpacheco.data.ServiceGeneric
import br.com.developer.desafioandroidandersonpacheco.model.characters.Characters
import br.com.developer.desafioandroidandersonpacheco.model.characters.CharactersData
import br.com.developer.desafioandroidandersonpacheco.model.CharactersResponse
import br.com.developer.desafioandroidandersonpacheco.model.comics.Comics
import br.com.developer.desafioandroidandersonpacheco.model.comics.DataComics
import br.com.developer.desafioandroidandersonpacheco.util.Constantes
import br.com.developer.desafioandroidandersonpacheco.util.Utils
import org.json.JSONObject
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class CharactersViewModel(application: Application) : AndroidViewModel(application) {

    private lateinit var mutableLiveDataCharacters: MutableLiveData<CharactersResponse<Characters>>
    private lateinit var mutableLiveDataComics: MutableLiveData<CharactersResponse<Comics>>

    fun getCharactersObservable(): MutableLiveData<CharactersResponse<Characters>> {
        if (!::mutableLiveDataCharacters.isInitialized) {
            mutableLiveDataCharacters = MutableLiveData()
        }
        return mutableLiveDataCharacters
    }

    fun getComicsObservable(): MutableLiveData<CharactersResponse<Comics>> {
        if (!::mutableLiveDataComics.isInitialized) {
            mutableLiveDataComics = MutableLiveData()
        }
        return mutableLiveDataComics
    }

    fun getDadosCharacters(timestamp: String,hashc: String) {
        val consultaService = ServiceGeneric.createService(CharactersService::class.java)
        val call = consultaService.characters("20",timestamp, Constantes.PUBLIC_KEY, hashc)

        call.enqueue(object : Callback<Characters> {
            override fun onResponse(
                call: Call<Characters>,
                response: Response<Characters>
            ) {
                try {
                    if (response.isSuccessful) {
                        val consultaCepResposta  = CharactersResponse<Characters>()
                        consultaCepResposta.data = response.body()
                        consultaCepResposta.message = response.message()
                        consultaCepResposta.resultCode = response.code()

                        mutableLiveDataCharacters.value = consultaCepResposta
                    } else {
                        var jObjError = JSONObject (response.errorBody()!!.string())
                        val consultaCepResposta: CharactersResponse<Characters> =
                            CharactersResponse(
                                null,
                                response.code(),
                                if (jObjError.get("message").toString().isNotEmpty()){
                                    jObjError.get("message").toString()
                                } else getApplication<Application>().resources.getString(R.string.msg_erro_conexao_servidor)
                            )

                        mutableLiveDataCharacters.value = consultaCepResposta
                    }
                } catch (e: Exception) {
                    val consultaCepResposta: CharactersResponse<Characters> =
                        CharactersResponse(
                            null,
                            0,
                            getApplication<Application>().resources.getString(R.string.msg_erro_conexao_servidor)
                        )

                    mutableLiveDataCharacters.value = consultaCepResposta
                }
            }

            override fun onFailure(call: Call<Characters>, t: Throwable) {
                val consultaCepResposta: CharactersResponse<Characters> =
                    CharactersResponse(
                        null, 0, Utils.validarMsgTimeout(getApplication(), t.message)
                    )

                mutableLiveDataCharacters.value = consultaCepResposta
            }
        })
    }

    fun getDadosComics(timestamp: String,hashc: String, characterId: String) {
        val consultaService = ServiceGeneric.createService(CharactersService::class.java)
        val call = consultaService.comics(characterId, timestamp, Constantes.PUBLIC_KEY, hashc)

        call.enqueue(object : Callback<Comics> {
            override fun onResponse(
                call: Call<Comics>,
                response: Response<Comics>
            ) {
                try {
                    if (response.isSuccessful) {
                        val consultaCepResposta  = CharactersResponse<Comics>()
                        consultaCepResposta.data = response.body()
                        consultaCepResposta.message = response.message()
                        consultaCepResposta.resultCode = response.code()

                        mutableLiveDataComics.value = consultaCepResposta
                    } else {
                        var jObjError = JSONObject (response.errorBody()!!.string())
                        val consultaCepResposta: CharactersResponse<Comics> =
                            CharactersResponse(
                                null,
                                response.code(),
                                if (jObjError.get("mensagem").toString().isNotEmpty()){
                                    when {
                                        jObjError.get("mensagem").toString().equals("very bad!") -> {
                                            ""
                                        }
                                        jObjError.get("mensagem").toString().equals("very bad") -> {
                                            ""
                                        }
                                        jObjError.get("mensagem").toString().equals("very bad.") -> {
                                            ""
                                        }
                                        jObjError.get("mensagem").toString().equals("too bad") -> {
                                            "Falha na rede. Tente novamente."
                                        }
                                        jObjError.get("mensagem").toString().equals("not so bad!!!") -> {
                                            ""
                                        }
                                        jObjError.get("mensagem").toString().equals("not so bad!!") -> {
                                            ""
                                        }
                                        jObjError.get("mensagem").toString().equals("not so bad!") -> {
                                            ""
                                        }
                                        jObjError.get("message").toString().equals("no name") -> {
                                            "no name"
                                        }
                                        //
                                        else -> jObjError.get("mensagem").toString()
                                    }
                                } else getApplication<Application>().resources.getString(R.string.msg_erro_conexao_servidor)
                            )

                        mutableLiveDataComics.value = consultaCepResposta
                    }
                } catch (e: Exception) {
                    val consultaCepResposta: CharactersResponse<Comics> =
                        CharactersResponse(
                            null,
                            0,
                            getApplication<Application>().resources.getString(R.string.msg_erro_conexao_servidor)
                        )

                    mutableLiveDataComics.value = consultaCepResposta
                }
            }

            override fun onFailure(call: Call<Comics>, t: Throwable) {
                val consultaCepResposta: CharactersResponse<Comics> =
                    CharactersResponse(
                        null, 0, Utils.validarMsgTimeout(getApplication(), t.message)
                    )

                mutableLiveDataComics.value = consultaCepResposta
            }
        })
    }
}