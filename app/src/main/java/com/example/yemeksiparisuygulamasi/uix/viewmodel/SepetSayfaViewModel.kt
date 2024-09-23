package com.example.yemeksiparisuygulamasi.uix.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.yemeksiparisuygulamasi.data.entity.SepetYemek
import com.example.yemeksiparisuygulamasi.data.repo.YemeklerRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class SepetSayfaViewModel @Inject constructor(val yrepo: YemeklerRepository) : ViewModel() {

    var sepetList = MutableLiveData<List<SepetYemek>>()
    val userName = "pelincoskun"
    var totalPrice = MutableLiveData<Int>()

  suspend  fun sepetiYukle() {
        viewModelScope.launch {
            try {
                val response = yrepo.sepetiYukle(userName)
                Log.d("SepetSayfa", "Response: $response")

                if (response.isNullOrEmpty()) {
                    sepetList.value = emptyList()
                } else {
                    val combinedItems = response.groupBy { it.yemek_adi }
                        .map { entry ->
                            val firstItem = entry.value.first()
                            firstItem.copy(
                                yemek_siparis_adet = entry.value.sumOf { it.yemek_siparis_adet.toInt() }.toString(),
                                ids = entry.value.map { it.sepet_yemek_id }
                            )
                        }

                    sepetList.value = combinedItems
                    toplamFiyatHesapla()
                    Log.e("SepetSayfa", "Sepeti yükleme hatası: ${sepetList.value}")
                }
            } catch (e: Exception) {
                Log.e("SepetSayfa", "Sepeti yükleme hatası: ${e.message}")
                sepetList.value = emptyList()
            }
        }
    }

      fun sepettenYemekSil(sepetYemekId: List<String>, userName: String) {
        viewModelScope.launch {
            try {
                for (id in sepetYemekId) {
                    yrepo.sepettenYemekSil(id.toInt(), userName)
                }
                sepetiYukle()
                toplamFiyatHesapla()
            } catch (e: Exception) {
                Log.e("SepetSayfa", "Yemek silme hatası: ${e.message}")
            }
        }
    }



    fun toplamFiyatHesapla() {
        viewModelScope.launch {
            totalPrice.value = sepetList.value?.sumOf { it.yemek_fiyat.toInt() * it.yemek_siparis_adet.toInt() } ?: 0
 }
    }
}




