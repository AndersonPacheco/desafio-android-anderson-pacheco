package br.com.developer.desafioandroidandersonpacheco.util

import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.net.Uri
import android.util.Base64
import br.com.developer.desafioandroidandersonpacheco.R
import java.io.ByteArrayOutputStream
import java.io.IOException
import java.io.InputStream
import java.io.UnsupportedEncodingException
import java.security.MessageDigest
import java.security.NoSuchAlgorithmException

object Utils {

    fun validarMsgTimeout(context: Context, msg: String?): String? {
        return if (!msg.isNullOrEmpty() && msg.contentEquals("timeout")) {
            context.getString(R.string.msg_erro_conexao_servidor)
        } else {
            msg
        }
    }

    fun md5Java(message: String): String? {
        var digest: String? = null
        try {
            val md = MessageDigest.getInstance("MD5")
            val hash = md.digest(message.toByteArray(charset("UTF-8")))
            val sb = StringBuilder(2 * hash.size)
            for (b in hash) {
                sb.append(String.format("%02x", b))
            }
            digest = sb.toString()
        } catch (ex: UnsupportedEncodingException) {
        } catch (ex: NoSuchAlgorithmException) {
        }
        return digest
    }

    }