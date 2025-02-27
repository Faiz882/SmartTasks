package com.example.taskproject.manager

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class ApiError {
    @SerializedName("message")
    var message: String? = null

    @SerializedName("code")
    @Expose
    var errorCode: String? = null
}