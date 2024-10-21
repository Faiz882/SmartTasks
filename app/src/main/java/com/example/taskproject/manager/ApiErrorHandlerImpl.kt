package com.example.taskproject.manager

import android.content.Context
import android.util.Log
import com.example.taskproject.R
import com.example.taskproject.ext.getStringByName
import com.example.taskproject.ext.isConnected
import com.faiz.coreui.errors.ApiErrorHandler
import com.faiz.coreui.errors.ApiErrorHandler.Companion.ERROR_CODE_OFFLINE
import com.faiz.coreui.errors.ApiErrorHandler.Companion.ERROR_CODE_TIMEOUT
import com.faiz.coreui.viewmodel.ViewModelState
import com.google.gson.GsonBuilder
import dagger.hilt.android.qualifiers.ApplicationContext
import retrofit2.HttpException
import java.net.ConnectException
import java.net.SocketTimeoutException
import java.net.UnknownHostException
import java.security.cert.CertificateException
import javax.inject.Inject
import javax.net.ssl.SSLHandshakeException


class ApiErrorHandlerImpl @Inject constructor(
    @ApplicationContext private val context: Context
) : ApiErrorHandler {

    override fun handleError(throwable: Throwable): ViewModelState.Error {
        when (throwable) {
            is UnknownHostException,
            is ConnectException,
            is SocketTimeoutException,
            is SSLHandshakeException,
            is CertificateException,
            -> {
                return if (context.isConnected()) {
                    ViewModelState.Error(
                        ERROR_CODE_TIMEOUT,
                        context.getString(R.string.common_connection_error_unstable_message)
                    )
                } else {
                    ViewModelState.Error(
                        ERROR_CODE_OFFLINE,
                        context.getString(R.string.common_connection_error_offline_message)
                    )
                }
            }

            is HttpException -> {
                return parseHttpException(throwable)
            }

            else -> {
                return ViewModelState.Error(error = context.getString(R.string.msg_service_unavailable))
            }
        }
    }

    private fun parseHttpException(
        throwable: HttpException
    ): ViewModelState.Error {
        val errJson = throwable.response()?.errorBody()?.string()
        Log.d("Error---> %s", errJson.toString())

        if (errJson.isNullOrEmpty()) {
            return ViewModelState.Error(error = context.getString(R.string.msg_service_unavailable))
        }

        val errorCode = parseErrorCode(errJson)

        val errorMessage = context.getStringByName("msg_error_$errorCode")
            ?: context.getString(R.string.msg_service_unavailable)

        return ViewModelState.Error(errorCode.toString(), errorMessage)
    }

    private fun parseErrorCode(errJson: String?): String? {
        return try {
            GsonBuilder().create().fromJson(errJson, ApiError::class.java).errorCode
        } catch (e: Exception) {
            e.printStackTrace()
            null
        }
    }

    private fun parseErrorMessage(errJson: String?): String? {
        return try {
            GsonBuilder().create().fromJson(errJson, ApiError::class.java).message
        } catch (e: Exception) {
            e.printStackTrace()
            null
        }
    }
}