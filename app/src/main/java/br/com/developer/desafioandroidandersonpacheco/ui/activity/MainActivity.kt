package br.com.developer.desafioandroidandersonpacheco.ui.activity

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.GridLayoutManager
import br.com.developer.desafioandroidandersonpacheco.R
import br.com.developer.desafioandroidandersonpacheco.databinding.ActivityMainBinding
import br.com.developer.desafioandroidandersonpacheco.model.characters.Characters
import br.com.developer.desafioandroidandersonpacheco.ui.adapter.ListaPersonagemAdapter
import br.com.developer.desafioandroidandersonpacheco.ui.viewmodel.CharactersViewModel
import br.com.developer.desafioandroidandersonpacheco.util.Alert
import br.com.developer.desafioandroidandersonpacheco.util.Constantes
import br.com.developer.desafioandroidandersonpacheco.util.ResultCode
import java.util.*
import br.com.developer.desafioandroidandersonpacheco.util.Utils

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    private val charactersViewModel by lazy {
        ViewModelProvider(this).get(
            CharactersViewModel::class.java
        )
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)

        binding.rvServicos.setHasFixedSize(true)
        binding.rvServicos.layoutManager = GridLayoutManager(this, 1)

        setToolbar()
        addObservableWithOnCreate()

        val calendar: Calendar = Calendar.getInstance()
        var ts = calendar.timeInMillis
        val stringToHash: String = calendar
            .timeInMillis.toString() + Constantes.PRIVATE_KEY + Constantes.PUBLIC_KEY
        var hash = Utils.md5Java(stringToHash)

        charactersViewModel.getDadosCharacters(ts.toString(), hash.toString())
        carregar(true)

    }

    private fun setToolbar() {
        setSupportActionBar(binding.toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(false)
    }

    private fun carregar(isCarregar: Boolean) {
        binding.pbLoading.visibility = if (isCarregar) View.VISIBLE else View.GONE
        binding.refClContainer.visibility = if (isCarregar) View.GONE else View.VISIBLE
    }

    private fun addObservableWithOnCreate() {
        charactersViewModel.getCharactersObservable()
            .observe(this, Observer { charactersResposta ->

                if (charactersResposta != null) {
                    when (charactersResposta.resultCode) {
                        ResultCode.SUCESS.value -> {
                            carregar(false)
                            val adapter = ListaPersonagemAdapter(this)
                            binding.rvServicos.adapter = adapter
                            var results = charactersResposta.data as Characters
                            for (i in 0 until results.data!!.results!!.size ) {
                                adapter.data.add(adapter.ServicoModel(
                                    results.data!!.results!![i].id!!.toString(),
                                    results.data!!.results!![i].name!!,
                                    results.data!!.results!![i].thumbnail!!.path.toString(),
                                    results.data!!.results!![i].description!!,
                                    target= DetalhePersonagemActivity::class.java))
                            }

                        }
                        ResultCode.NOTFOUND.value -> {
                            carregar(false)
                            Alert.mostrarDialogSimples(charactersResposta.message.toString(), this)
                        }
                        ResultCode.BADREQUEST.value -> {
                            carregar(false)
                            Alert.mostrarDialogSimples(charactersResposta.message.toString(), this)
                        }
                        ResultCode.INTERNALSERVER.value -> {
                            carregar(false)
                            Alert.mostrarDialogSimples(
                                charactersResposta.message.toString(),
                                this
                            )
                        }
                        ResultCode.AUTENTIFICATION.value -> {
                            carregar(false)
                            Alert.mostrarDialogSimples(
                                charactersResposta.message.toString(),
                                this
                            )
                        }
                    }
                }
            })
    }

    override fun onBackPressed() {
        super.onBackPressed()
        finish()
    }
}
