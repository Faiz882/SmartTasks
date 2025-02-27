package network.di

import android.content.Context
import network.interceptor.RequestInterceptor
import com.andyha.corenetwork.qualifier.ForLogging
import com.andyha.corenetwork.qualifier.ForRequestInterceptor
import com.google.gson.FieldNamingPolicy
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import network.ResponseConverter
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import javax.inject.Singleton


@InstallIn(SingletonComponent::class)
@Module
class BaseNetworkModule {

    // =============================================================================================
    // Shared

    @Singleton
    @Provides
    fun provideGson(): Gson = GsonBuilder()
        .setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES)
        .setDateFormat("yyyy-MM-dd HH:mm:ss")
        .disableHtmlEscaping()
        .setPrettyPrinting()
        .create()


    @Singleton
    @Provides
    fun provideRetrofitBuilder(gson: Gson, okHttpClient: OkHttpClient): Retrofit.Builder =
        Retrofit.Builder()
            .addConverterFactory(ResponseConverter(gson))
            .client(okHttpClient)


    // =============================================================================================
    // Interceptor(s)

    @Singleton
    @Provides
    @ForRequestInterceptor
    fun provideRequestInterceptor(
        @ApplicationContext context: Context,
    ): Interceptor = RequestInterceptor()


    @Singleton
    @Provides
    @ForLogging
    fun provideLoggingInterceptor(): Interceptor =  HttpLoggingInterceptor().apply { level = HttpLoggingInterceptor.Level.BODY }

}