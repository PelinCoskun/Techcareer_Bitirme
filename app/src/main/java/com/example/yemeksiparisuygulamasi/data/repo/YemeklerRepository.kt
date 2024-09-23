package com.example.yemeksiparisuygulamasi.data.repo

import com.example.yemeksiparisuygulamasi.data.datasource.YemeklerDataSource
import com.example.yemeksiparisuygulamasi.data.entity.SepetYemek
import com.example.yemeksiparisuygulamasi.data.entity.Yemekler

class YemeklerRepository(var yds: YemeklerDataSource) {
    suspend fun yemekleriYukle(): List<Yemekler> = yds.yemekleriYukle()

    suspend fun sepeteYemekEkle(yemek_adi: String, yemek_resim_adi: String, yemek_fiyat: Int, yemek_siparis_adet: Int, kullanici_adi: String)  {
        yds.sepeteYemekEkle(yemek_adi, yemek_resim_adi, yemek_fiyat, yemek_siparis_adet, kullanici_adi)
    }

    suspend fun sepetiYukle(kullanici_adi: String): List<SepetYemek> = yds.sepetiYukle(kullanici_adi)

    suspend fun sepettenYemekSil(sepet_yemek_id: Int, kullanici_adi: String) =
        yds.sepettenYemekSil(sepet_yemek_id, kullanici_adi)


}