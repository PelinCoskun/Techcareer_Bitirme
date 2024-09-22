package com.example.yemeksiparisuygulamasi.data.datasource

import com.example.yemeksiparisuygulamasi.data.entity.SepetYemek
import com.example.yemeksiparisuygulamasi.data.entity.Yemekler
import com.example.yemeksiparisuygulamasi.retrofit.YemeklerDao
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class YemeklerDataSource(var ydao: YemeklerDao) {

    suspend fun yemekleriYukle(): List<Yemekler> = withContext(Dispatchers.Main) {
      return@withContext ydao.yemekleriYukle().yemekler
    }

    suspend fun sepeteYemekEkle(yemek_adi: String, yemek_resim_adi: String, yemek_fiyat: Int, yemek_siparis_adet: Int, kullanici_adi: String)  {
         ydao.sepeteYemekEkle(yemek_adi, yemek_resim_adi, yemek_fiyat, yemek_siparis_adet, kullanici_adi)
    }

    suspend fun sepetiYukle(kullanici_adi: String): List<SepetYemek> = withContext(Dispatchers.Main) {
        val response = ydao.sepetiYukle(kullanici_adi)

        return@withContext response.sepet_yemekler
    }

    suspend fun sepettenYemekSil(sepet_yemek_id: Int, kullanici_adi: String) {
        ydao.sepettenYemekSil(sepet_yemek_id, kullanici_adi)
    }

}