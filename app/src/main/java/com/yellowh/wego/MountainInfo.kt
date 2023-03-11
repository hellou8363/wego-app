package com.yellowh.wego

data class MountainInfo(
    val infoCD: Int,
    val sanName: String,
    val height: Int,
    val address: String,
    val reason: String,
    val overview: String,
    val details: String,
    val lat: Int,
    val lon: Int,
    val img: String,
    val likeCNT: Int
)