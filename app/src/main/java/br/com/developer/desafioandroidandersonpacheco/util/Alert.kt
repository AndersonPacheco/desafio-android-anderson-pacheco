package br.com.developer.desafioandroidandersonpacheco.util

import android.content.Context
import android.text.Html

object Alert {

    fun mostrarDialogSimples(message: String, context: Context) {
        val builder = android.app.AlertDialog.Builder(context)
        builder.setMessage(Html.fromHtml(message))
        builder.setTitle(Constantes.ALERT_AVISO)
        builder.setPositiveButton(Constantes.ALERT_OK, null)
        builder.show()
    }
}
