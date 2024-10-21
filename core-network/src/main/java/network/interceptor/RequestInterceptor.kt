package network.interceptor

import android.os.Build
import network.config.NetworkConfigConstants
import okhttp3.Interceptor
import okhttp3.Response
import javax.inject.Inject

class RequestInterceptor @Inject constructor() : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val builder = chain.request().newBuilder()

        if (chain.request().header("unAuthorized") == null){
            val accessToken = "" // place you access token from local storage
            if (accessToken.isNotEmpty()) {
                builder.header(NetworkConfigConstants.AUTHORIZATION, "${NetworkConfigConstants.BEARER} $accessToken")
            }
        }else{
            builder.removeHeader("unAuthorized")
        }

        builder.header("X-Device-Platform", "android")
        builder.header("X-Device-OS-Version", "android ${Build.VERSION.RELEASE}") //android 10
        builder.header("Content-Type", "application/json")
        return chain.proceed(builder.build())
    }
}