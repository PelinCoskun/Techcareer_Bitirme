package com.example.yemeksiparisuygulamasi.retrofit

import retrofit2.create

object ApiUtils {

    val BASE_URL = "http://kasimadalan.pe.hu/"

    fun getYemeklerDao(): YemeklerDao {
        return RetrofitClient.getClient(BASE_URL).create(YemeklerDao::class.java)
    }
}