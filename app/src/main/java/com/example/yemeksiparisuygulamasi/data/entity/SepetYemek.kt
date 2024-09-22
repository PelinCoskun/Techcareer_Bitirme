package com.example.yemeksiparisuygulamasi.data.entity

import kotlinx.serialization.SerialName


data class SepetYemek(
    @SerialName("sepet_yemek_id")   var sepet_yemek_id: String,
    @SerialName("yemek_adi")  var yemek_adi: String,
    @SerialName("yemek_resim_adi")  var yemek_resim_adi: String,
    @SerialName("yemek_fiyat")  var yemek_fiyat: String,
    @SerialName("yemek_siparis_adet")  var yemek_siparis_adet: String,
    @SerialName("kullanici_adi")  var kullanici_adi: String,
     var ids: List<String>?,
)