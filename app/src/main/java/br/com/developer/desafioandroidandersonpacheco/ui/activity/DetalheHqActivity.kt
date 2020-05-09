package br.com.developer.desafioandroidandersonpacheco.ui.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import android.view.View
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import br.com.developer.desafioandroidandersonpacheco.R
import br.com.developer.desafioandroidandersonpacheco.databinding.ActivityDetalheHqBinding
import br.com.developer.desafioandroidandersonpacheco.model.comics.Comics
import br.com.developer.desafioandroidandersonpacheco.model.comics.ComicsResults
import br.com.developer.desafioandroidandersonpacheco.ui.viewmodel.CharactersViewModel
import br.com.developer.desafioandroidandersonpacheco.util.Alert
import br.com.developer.desafioandroidandersonpacheco.util.Constantes
import br.com.developer.desafioandroidandersonpacheco.util.ResultCode
import br.com.developer.desafioandroidandersonpacheco.util.Utils
import com.squareup.picasso.Picasso
import java.util.*

class DetalheHqActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDetalheHqBinding

    private val charactersViewModel by lazy {
        ViewModelProviders.of(this).get(
            CharactersViewModel::class.java
        )
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_detalhe_hq)

        setToolbar()
        carregar(false)

        addObservableWithOnCreate()

        val calendar: Calendar = Calendar.getInstance()
        var ts = calendar.timeInMillis
        val stringToHash: String = calendar
            .timeInMillis.toString() + Constantes.PRIVATE_KEY + Constantes.PUBLIC_KEY
        var hash = Utils.md5Java(stringToHash)
        charactersViewModel.getDadosComics(ts.toString(), hash.toString(), intent.getStringExtra("PARAM_ID"))
    }

    private fun setToolbar() {
        setSupportActionBar(binding.toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }

    private fun carregar(isCarregar: Boolean) {
        binding.pbLoading.visibility = if (isCarregar) View.VISIBLE else View.GONE
        binding.refClContainer.visibility = if (isCarregar) View.GONE else View.VISIBLE
    }

    private fun addObservableWithOnCreate() {
        charactersViewModel.getComicsObservable()
            .observe(this, Observer { comicsResposta ->

                if (comicsResposta != null) {
                    when (comicsResposta.resultCode) {
                        ResultCode.SUCESS.value -> {
                            carregar(false)
                            var resultsList: ArrayList<ComicsResults>? = null
                            var results = comicsResposta.data as Comics

//                            resultsList = results!!.data!!.results!!
//                            for (i in 0 until results!!.data!!.results!!.size){
//                                if (resultsList[i])
//                            }
                            Picasso.with(this)
                                .load("${results!!.data!!.results!![0].thumbnail!!.path}" +
                                        "/portrait_uncanny." +
                                        "${results!!.data!!.results!![0].thumbnail!!.extension}")
                                .into(binding.ivIcon)

                            binding.tvTitulo.text = results!!.data!!.results!![0].title
                            binding.tvDescricao.text = results!!.data!!.results!![0].description
                            binding.tvPreco.text = "$${results!!.data!!.results!![0].prices!![0].price.toString()}"
                        }
                        ResultCode.NOTFOUND.value -> {
                            carregar(false)
                            Alert.mostrarDialogSimples(comicsResposta.message.toString(), this)
                        }
                        ResultCode.CONFLICT.value -> {
                            carregar(false)
                            Alert.mostrarDialogSimples(comicsResposta.message.toString(), this)
                        }
                        ResultCode.INTERNALSERVER.value -> {
                            carregar(false)
                            Alert.mostrarDialogSimples(
                                comicsResposta.message.toString(),
                                this
                            )
                        }
                        ResultCode.AUTENTIFICATION.value -> {
                            carregar(false)
                            Alert.mostrarDialogSimples(
                                comicsResposta.message.toString(),
                                this
                            )
                        }
                    }
                }
            })
    }

    override fun onOptionsItemSelected(item: MenuItem) = when (item.itemId) {
        android.R.id.home ->
        {
            finish(); true
        } else -> false
    }

    override fun onBackPressed() {
        super.onBackPressed()
        finish()
    }
}
