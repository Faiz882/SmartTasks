package network.di

import com.andyha.corenetwork.qualifier.ForLogging
import com.andyha.corenetwork.qualifier.ForRequestInterceptor
import com.google.gson.Gson
import network.qualifier.TaskService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import network.ResponseConverter
import network.config.NetworkConfig
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import java.security.SecureRandom
import java.security.cert.X509Certificate
import java.util.concurrent.TimeUnit
import javax.inject.Singleton
import javax.net.ssl.SSLContext
import javax.net.ssl.SSLSocketFactory
import javax.net.ssl.TrustManager
import javax.net.ssl.X509TrustManager

@Module
@InstallIn(SingletonComponent::class)
class TaskModule {
    @Singleton
    @Provides
    @TaskService
    fun provideTaskManagementBaseUrl(networkConfig: NetworkConfig) = networkConfig.apiBaseUrl

    @Singleton
    @Provides
    @TaskService
    fun provideHttpClientForAuthenticator(
        @ForRequestInterceptor tokenInterceptor: Interceptor,
        @ForLogging logging: Interceptor?,
    ): OkHttpClient {
        val httpClient = OkHttpClient.Builder().apply {
            sslSocketFactory(getUnsafeSSLSocketFactory(), getUnsafeTrustManager())  // don't use for production
            hostnameVerifier { _, _ -> true }
            connectTimeout(50, TimeUnit.SECONDS)
            readTimeout(50, TimeUnit.SECONDS)
            writeTimeout(50, TimeUnit.SECONDS)
            logging?.let { addInterceptor(it) }
        }
        return httpClient.build()
    }

    @Singleton
    @Provides
    @TaskService
    fun provideRetrofitForTaskService(
        @TaskService baseUrl: String,
        gson: Gson,
        @TaskService okHttpClient: OkHttpClient
    ): Retrofit = Retrofit.Builder()
        .baseUrl(baseUrl)
        .addConverterFactory(ResponseConverter(gson))
        .client(okHttpClient)
        .build()

    //TODO: Server has some certificate issue so I'm allowing all certificates for a time being but don't include for Production Env.
    private fun getUnsafeSSLSocketFactory(): SSLSocketFactory {
        val trustAllCerts = arrayOf<TrustManager>(object : X509TrustManager {
            override fun checkClientTrusted(chain: Array<out X509Certificate>?, authType: String?) {}
            override fun checkServerTrusted(chain: Array<out X509Certificate>?, authType: String?) {}
            override fun getAcceptedIssuers(): Array<X509Certificate> = arrayOf()
        })

        val sslContext = SSLContext.getInstance("SSL")
        sslContext.init(null, trustAllCerts, SecureRandom())
        return sslContext.socketFactory
    }

    private fun getUnsafeTrustManager(): X509TrustManager {
        return object : X509TrustManager {
            override fun checkClientTrusted(chain: Array<out X509Certificate>?, authType: String?) {}
            override fun checkServerTrusted(chain: Array<out X509Certificate>?, authType: String?) {}
            override fun getAcceptedIssuers(): Array<X509Certificate> = arrayOf()
        }
    }
}