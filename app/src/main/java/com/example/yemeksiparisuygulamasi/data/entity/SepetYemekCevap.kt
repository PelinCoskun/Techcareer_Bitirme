package com.example.yemeksiparisuygulamasi.data.entity

import kotlinx.serialization.SerialName


data class SepetYemekCevap(
    @SerialName("sepet_yemekler") var sepet_yemekler: List<SepetYemek>,
    var success: Int)