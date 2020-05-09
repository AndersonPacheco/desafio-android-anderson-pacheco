package br.com.developer.desafioandroidandersonpacheco.util

enum class ResultCode(val value: Int) {
    SUCESS(200),
    CREATED(201),
    BADREQUEST(400),
    NOCONTENT(204),
    INTERNALSERVER(500),
    CONFLICT(409),
    NOTFOUND(404),
    ERRORAUTHORIZED(401),
    ACCEPTERD(202),
    AUTENTIFICATION(403)
}