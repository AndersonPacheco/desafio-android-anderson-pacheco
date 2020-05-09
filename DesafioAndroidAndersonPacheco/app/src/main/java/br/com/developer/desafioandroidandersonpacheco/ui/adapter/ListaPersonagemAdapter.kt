package br.com.developer.desafioandroidandersonpacheco.ui.adapter

import android.app.Activity
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.drawable.Drawable
import android.util.Base64
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import br.com.developer.desafioandroidandersonpacheco.R
import com.squareup.picasso.Picasso
import java.io.IOException
import java.io.InputStream
import java.net.MalformedURLException
import java.net.URL


class ListaPersonagemAdapter(val ctx: Activity): RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    val data: ArrayList<ServicoModel> = ArrayList()

    override fun getItemCount() = data.size
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = ServicoView()
    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) = if (holder is ServicoView) holder.bind(data[position]) else {}


    inner class ServicoView : RecyclerView.ViewHolder(LayoutInflater.from(ctx).inflate(R.layout.view_lista_personagem, null, false)) {
        private val tvNome: TextView
        private val ivFoto: ImageView
        private val clView: View


        init {
            itemView.layoutParams = RecyclerView.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT)

            tvNome = itemView.findViewById(R.id.tv_descricao)
            ivFoto = itemView.findViewById(R.id.iv_icon)
            clView = itemView.findViewById<View>(R.id.cl_view)
        }

        fun bind(obj: ServicoModel) {
            tvNome.text = obj.nome
            Picasso.with(ctx).load("${obj.foto}/portrait_xlarge.jpg").into(ivFoto)
            itemView.setOnClickListener { ctx.startActivity(
                Intent(ctx, obj.target)
                    .putExtra("PARAM_ID", obj.id)
                    .putExtra("PARAM_NOME", obj.nome)
                    .putExtra("PARAM_FOTO", obj.foto)
                    .putExtra("PARAM_DESCRICAO", obj.descricao)) }
        }
    }
    inner class ServicoModel(
        val id: String,
        val nome: String,
        val foto: String,
        val descricao: String,
        val target: Class<out Activity>?)

    private fun LoadImageFromWebOperations(url: String): Drawable? {
        return try {
            val `is` = URL(url).content as InputStream
            Drawable.createFromStream(`is`, "src name")
        } catch (e: Exception) {
            println("Exc=$e")
            null
        }
    }
}