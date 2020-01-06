package com.greenhackers.iot2

data class Response(
    val _id: Id,
    val humidity: String,
    val temperature: String,
    val time: String
)

data class Id(
    val `$oid`: String
)