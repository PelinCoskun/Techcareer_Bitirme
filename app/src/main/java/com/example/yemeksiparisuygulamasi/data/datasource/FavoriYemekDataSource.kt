package com.example.yemeksiparisuygulamasi.data.datasource

import com.example.yemeksiparisuygulamasi.data.entity.FavoriYemek
import com.example.yemeksiparisuygulamasi.room.FavoriYemekDao
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class FavoriYemekDataSource (var fdao: FavoriYemekDao) {

    suspend fun favorileriYukle(): List<FavoriYemek> = withContext(Dispatchers.IO) {
        return@withContext fdao.tumFavoriler()
    }


    suspend fun favoriEkle(yemek_adi: String, yemek_resim: String, yemek_fiyat: Int) = withContext(Dispatchers.IO) {
        val mevcutFavori = fdao.getFavoriYemekByName(yemek_adi)
        if (mevcutFavori == null) {
            val yeniFavori = FavoriYemek(yemek_adi = yemek_adi, yemek_resim = yemek_resim, yemek_fiyat = yemek_fiyat)
            fdao.favoriEkle(yeniFavori)
        }
    }

    suspend fun favoriSil(yemek_id: Int) = withContext(Dispatchers.IO) {
        val silinenFavori = FavoriYemek(yemek_id, "", "", 0)
        fdao.favoriSil(silinenFavori)
    }

    suspend fun favoriSilByYemekAdi(yemek_adi: String) = withContext(Dispatchers.IO) {
        fdao.favoriSilByYemekAdi(yemek_adi)
    }
}