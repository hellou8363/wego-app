package com.yellowh.wego

data class Recruitment(
    val sanName: String,
    val userID: Int,
    val nickName: String,
    val userPic: String,
    val createdDT: String,
    val modifiedDT: String,
    val title: String,
    val sanPartyID: Int,
    val contents: String,
    val partyYMD: String,
    val partyTime: String,
    val partyMax: Int,
    val userCnt: Int,
    val items: String,
    val condition: String,
    val likeCnt: Int,
    val reportCnt: Int
)