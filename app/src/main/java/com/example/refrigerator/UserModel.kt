package com.example.loginapp

import com.google.gson.annotations.SerializedName
data class  UserModel(
    @SerializedName("id") val id: Int,
    @SerializedName("firstname") var firstName: String,
    @SerializedName("lastname") var lastName: String,
    @SerializedName("username") var username: String,
    @SerializedName("phonenumber") var phonenumber: String,
    @SerializedName("password") var password: String
)