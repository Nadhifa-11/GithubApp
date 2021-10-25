package com.nadhifa.githubapp.model

data class Users(
    var username : String? = null,
    var name : String? = " ",
    var email : String? = " ",
    var company : String? = " ",
    var location : String? = " ",
    var followers : Int? = 0,
    var following : Int? =0,
    var avatar : String? = null,
)