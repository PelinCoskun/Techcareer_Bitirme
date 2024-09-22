package com.example.yemeksiparisuygulamasi.uix.viewmodel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.yemeksiparisuygulamasi.data.entity.FavoriYemek
import com.example.yemeksiparisuygulamasi.data.repo.FavoriYemekRepository
import com.example.yemeksiparisuygulamasi.data.repo.YemeklerRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FavoriSayfaViewModel @Inject constructor(var frepo:FavoriYemekRepository) :  ViewModel(){

    var favoriListesi = MutableLiveData<List<FavoriYemek>>()

    init {
       favorileriYukle()
    }

    fun favorileriYukle(){
       CoroutineScope(Dispatchers.Main).launch{
           favoriListesi.value=frepo.favorileriYukle()
       }
    }

    fun favoriEkle(yemek_adi: String, yemek_resim: String, yemek_fiyat: Int) {
        CoroutineScope(Dispatchers.Main).launch {
            frepo.favoriEkle(yemek_adi, yemek_resim, yemek_fiyat)
            favorileriYukle()
        }
    }
    fun favoriSil(yemek_id: Int) {
        CoroutineScope(Dispatchers.Main).launch {
            frepo.favoriSil(yemek_id)
            Log.d("FavoriSil", "Yemek silindi: $yemek_id")
            favorileriYukle()
        }
    }

    fun favoriSilByYemekAdi(yemek_adi: String) {
        CoroutineScope(Dispatchers.Main).launch {
            frepo.favoriSilByYemekAdi(yemek_adi)
            favorileriYukle()
        }
    }

    fun isFavorited(yemek_adi: String): Boolean {
        // Favorilerdeki yemekleri kontrol ederek, var mı yok mu bakabilirsiniz
        return favoriListesi.value?.any { it.yemek_adi == yemek_adi } ?: false
    }


}