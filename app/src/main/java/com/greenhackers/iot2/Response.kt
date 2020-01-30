package com.greenhackers.iot2

data class Response(
    val _id: Id,
    val humidity: Humidity,
    val temperature: Temperature
)

data class Id(
    val `$oid`: Any
)

data class Humidity(
    val `$numberDouble`: Any
)

data class Temperature(
    val `$numberDouble`: Any
)