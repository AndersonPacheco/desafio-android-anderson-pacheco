package br.com.developer.desafioandroidandersonpacheco.ui.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import android.view.View
import androidx.databinding.DataBindingUtil
import br.com.developer.desafioandroidandersonpacheco.R
import br.com.developer.desafioandroidandersonpacheco.databinding.ActivityDetalhePersonagemBinding
import com.squareup.picasso.Picasso

class DetalhePersonagemActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDetalhePersonagemBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_detalhe_personagem)

        overridePendingTransition(R.anim.fade_in, R.anim.fade_out)

        setToolbar()

        binding.tvNome.text = intent.getStringExtra("PARAM_NOME")
        binding.tvDescricao.text = intent.getStringExtra("PARAM_DESCRICAO")
        Picasso.with(this).load("${intent.getStringExtra("PARAM_FOTO")}/portrait_uncanny.jpg").into(binding.ivIcon)

        binding.btnHq.setOnClickListener {
            startActivity(Intent(this, DetalheHqActivity::class.java)
                .putExtra("PARAM_ID", intent.getStringExtra("PARAM_ID")))
        }
    }

    private fun setToolbar() {
        setSupportActionBar(binding.toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
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
