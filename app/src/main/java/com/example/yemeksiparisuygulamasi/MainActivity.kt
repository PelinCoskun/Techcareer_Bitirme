package com.example.yemeksiparisuygulamasi

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.rememberNavController
import com.example.yemeksiparisuygulamasi.ui.theme.YemekSiparisUygulamasiTheme
import com.example.yemeksiparisuygulamasi.uix.viewmodel.AnasayfaViewModel
import com.example.yemeksiparisuygulamasi.uix.viewmodel.FavoriSayfaViewModel
import com.example.yemeksiparisuygulamasi.uix.viewmodel.SepetSayfaViewModel
import com.example.yemeksiparisuygulamasi.uix.viewmodel.YemekDetaySayfaViewModel
import com.example.yemeksiparisuygulamasi.uix.views.Anasayfa
import com.example.yemeksiparisuygulamasi.uix.views.BottomBarSayfa
import com.example.yemeksiparisuygulamasi.uix.views.SayfaGecisleri
import com.example.yemeksiparisuygulamasi.uix.views.SepetSayfa
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    val anasayfaViewModel : AnasayfaViewModel by viewModels()
    val sepetSayfaViewModel: SepetSayfaViewModel by viewModels()
    val yemekDetaySayfaViewModel: YemekDetaySayfaViewModel by viewModels()
    val favoriSayfaViewModel: FavoriSayfaViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            YemekSiparisUygulamasiTheme {
            SayfaGecisleri(anasayfaViewModel,sepetSayfaViewModel,yemekDetaySayfaViewModel,favoriSayfaViewModel)
        }
    }}
}

