package com.d3if4063.assesment3_kalkulatorku.network

import com.d3if4063.assesment3_kalkulatorku.model.GaleriModel
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET

private const val BASE_URL = "https://raw.githubusercontent.com/" +
        "indraazimi/galeri-hewan/static-api/"

private val moshi = Moshi.Builder()
    .add(KotlinJsonAdapterFactory())
    .build()

private val retrofit = Retrofit.Builder()
    .addConverterFactory(MoshiConverterFactory.create(moshi))
    .baseUrl(BASE_URL)
    .build()

interface DataApiService {
    @GET("static-api.json")
    suspend fun getData(): List<GaleriModel>
}

object DataApi{
    val service: DataApiService by lazy {
        retrofit.create(DataApiService::class.java)
    }

    fun getDataUrl(nama: String): String {
        return "$BASE_URL$nama.jpg"
    }
}

enum class ApiStatus { LOADING, SUCCESS, FAILED }