package com.example.yemeksiparisuygulamasi.data.repo

import com.example.yemeksiparisuygulamasi.data.datasource.FavoriYemekDataSource
import com.example.yemeksiparisuygulamasi.data.entity.FavoriYemek
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class FavoriYemekRepository  @Inject constructor(var fds: FavoriYemekDataSource) {
    suspend fun favorileriYukle(): List<FavoriYemek> =fds.favorileriYukle()

    suspend fun favoriEkle(yemek_adi: String, yemek_resim: String, yemek_fiyat: Int) =
        fds.favoriEkle(yemek_adi, yemek_resim, yemek_fiyat)

    suspend fun favoriSil(yemek_id: Int) = fds.favoriSil(yemek_id)

    suspend fun favoriSilByYemekAdi(yemek_adi: String) = fds.favoriSilByYemekAdi(yemek_adi)
}