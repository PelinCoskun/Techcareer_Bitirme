package com.example.yemeksiparisuygulamasi.uix.viewmodel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.yemeksiparisuygulamasi.data.entity.SepetYemek
import com.example.yemeksiparisuygulamasi.data.repo.YemeklerRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class YemekDetaySayfaViewModel @Inject constructor(var yrepo: YemeklerRepository) : ViewModel() {

    var sepetList = MutableLiveData<List<SepetYemek>>()
    var userName = "pelincoskun"

    fun sepeteEkle(foodName: String, foodImageName: String, foodPrice: Int, foodQuantity: Int, userName: String="pelincoskun") {
        CoroutineScope(Dispatchers.Main).launch {
            Log.d("SepetEkleCevap", "Cevap: ${yrepo.sepeteYemekEkle(foodName, foodImageName, foodPrice, foodQuantity, userName)}")
            sepetiYukle()
        }
    }

    fun sepetiYukle() {
        CoroutineScope(Dispatchers.Main).launch {
            try {
                sepetList.value = yrepo.sepetiYukle(userName)
            } catch (e: Exception) {
                sepetList.value = emptyList()
            }
        }
    }

    fun miktarArttir(quantity: String): Int {
        return if (quantity != "") {
            quantity.toInt() + 1
        } else {
            1
        }
    }

    fun miktarAzalt(quantity: String): Int {
        return if (quantity != "") {
            quantity.toInt() - 1
        } else {
            1
        }
    }

    fun miktarKontrol(quantity: String): String {
        return if (quantity != "") {
            when {
                quantity.toInt() <= 0 -> "1"
                quantity.toInt() > 999 -> "999"
                else -> quantity
            }
        } else {
            ""
        }
    }

    fun toplamGuncelle(quantity: String, price: Int): Int {
        return if (quantity != "") {
            quantity.toInt() * price
        } else {
            price
        }
    }
}


