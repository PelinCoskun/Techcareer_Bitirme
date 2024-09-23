package com.example.yemeksiparisuygulamasi.uix.views

import android.annotation.SuppressLint
import android.util.Log
import androidx.activity.compose.BackHandler
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.yemeksiparisuygulamasi.R
import com.example.yemeksiparisuygulamasi.data.entity.SepetYemek
import com.example.yemeksiparisuygulamasi.ui.theme.Anarenk
import com.example.yemeksiparisuygulamasi.ui.theme.Anarenk2
import com.example.yemeksiparisuygulamasi.ui.theme.Anarenk3
import com.example.yemeksiparisuygulamasi.ui.theme.Anarenk4
import com.example.yemeksiparisuygulamasi.uix.viewmodel.SepetSayfaViewModel
import com.skydoves.landscapist.glide.GlideImage
import kotlinx.coroutines.launch

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable

fun SepetSayfa(navController: NavController, sepetSayfaViewModel: SepetSayfaViewModel,  secilenItem: MutableState<Int>) {
    val sepetList by sepetSayfaViewModel.sepetList.observeAsState(listOf())
    val scope = rememberCoroutineScope()

    BackHandler(enabled = true) {
        Log.d("SepetSayfa", "Geri tuşuna basıldı.")
        secilenItem.value = 0
        navController.navigate("anasayfa") {
        }
    }

    LaunchedEffect(Unit) {
        with(sepetSayfaViewModel) {
            scope.launch {
                sepetiYukle()
                toplamFiyatHesapla()
            }

        }
  }

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Box(
                        modifier = Modifier
                            .fillMaxWidth(),
                                contentAlignment = Alignment.Center
                    ) {
                        Text(
                            text = "Sepetim",
                            modifier = Modifier.align(Alignment.TopCenter),
                            style = MaterialTheme.typography.headlineLarge
                        )
                    }
                },
                colors = TopAppBarDefaults.smallTopAppBarColors(
                    containerColor = Color.White
                )
            )
        },
        containerColor = Color.White,
        content = { paddingValues ->
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .fillMaxWidth()
                    .padding(paddingValues)
                    .padding(16.dp)
            ) {
                if (sepetList.isNullOrEmpty()) {
                    Text(text = "Sepetiniz boş.")
                } else {
                    LazyColumn(
                        modifier = Modifier.weight(1f)
                    ) {
                        items(sepetList) { item ->
                            SepetItemRow(item, onDelete = {
                                sepetSayfaViewModel.sepettenYemekSil(item.ids!!, sepetSayfaViewModel.userName)
                            })
                        }
                    }

                    Spacer(modifier = Modifier.height(16.dp))
                    Text(
                        text = "Toplam Tutar: ${sepetSayfaViewModel.totalPrice.value}₺",
                        modifier = Modifier.align(Alignment.CenterHorizontally)
                    )
                }
            }
        }
    )


}

@Composable
fun SepetItemRow(item: SepetYemek, onDelete: () -> Unit) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp)
            .border(1.dp, Anarenk2, shape = RoundedCornerShape(15.dp)),
        colors = CardDefaults.cardColors(containerColor = Color.White)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {

            Row(verticalAlignment = Alignment.CenterVertically) {

                GlideImage(
                    imageModel = "http://kasimadalan.pe.hu/yemekler/resimler/${item.yemek_resim_adi}",
                    modifier = Modifier
                        .size(120.dp)
                        .clip(RoundedCornerShape(8.dp))
                )

                Spacer(modifier = Modifier.width(16.dp))

                Column {
                    Text(text = item.yemek_adi!!)
                    Text(text = "Adet: ${item.yemek_siparis_adet}")
                    Text(text = "Fiyat: ${item.yemek_fiyat} ₺")
                }
            }

            IconButton(onClick = onDelete) {
                Icon(Icons.Default.Delete,
                    contentDescription = "Yemek Sil",
                    tint = Anarenk,
                    modifier = Modifier.size(30.dp)
                )
            }
        }
    }
}










