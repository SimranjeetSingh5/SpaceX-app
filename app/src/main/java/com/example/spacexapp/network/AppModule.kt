package com.example.spacexapp.network

import android.content.Context
import androidx.room.Room
import com.example.spacexapp.BuildConfig
import com.example.spacexapp.utils.Constants.Companion.BASE_URL
import com.example.spacexapp.repository.RocketRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object RetrofitModule{


    @Provides
    @Singleton
    fun provideOkHttpClient() = if (BuildConfig.DEBUG) {
        val loggingInterceptor = HttpLoggingInterceptor()
        loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY)
        OkHttpClient.Builder()
            .addInterceptor(loggingInterceptor)
            .build()
    } else OkHttpClient
        .Builder()
        .build()

    @Singleton
    @Provides
    fun provideRocketApi():ApiService = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()
        .create(ApiService::class.java)








//    @Singleton
//    @Provides
//    fun provideYourDatabase(
//        @ApplicationContext app: Context
//    ) = Room.databaseBuilder(
//        app,
//        RocketDatabase::class.java,
//        "rockets_database"
//    ).build()
//
//    @Singleton
//    @Provides
//    fun provideYourDao(db: RocketDatabase) = db.getRocketDao()


}
