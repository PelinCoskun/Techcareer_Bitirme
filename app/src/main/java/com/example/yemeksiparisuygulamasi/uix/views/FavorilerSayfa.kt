package com.example.yemeksiparisuygulamasi.uix.views

import android.util.Log
import androidx.activity.compose.BackHandler
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
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
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.yemeksiparisuygulamasi.R
import com.example.yemeksiparisuygulamasi.ui.theme.Anarenk
import com.example.yemeksiparisuygulamasi.uix.viewmodel.AnasayfaViewModel
import com.example.yemeksiparisuygulamasi.uix.viewmodel.FavoriSayfaViewModel
import com.skydoves.landscapist.glide.GlideImage


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FavorilerSayfa(
    navController: NavController,
    favoriSayfaViewModel: FavoriSayfaViewModel,
    anasayfaViewModel: AnasayfaViewModel,
    secilenItem: MutableState<Int>
) {
    BackHandler(enabled = true) {
        Log.d("SepetSayfa", "Geri tuşuna basıldı.")
        secilenItem.value = 0
        navController.navigate("anasayfa") {
        }
    }

    val favoriListesi by favoriSayfaViewModel.favoriListesi.observeAsState(listOf())

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Box(
                        modifier = Modifier.fillMaxWidth(),
                        contentAlignment = Alignment.Center
                    ) {
                        Text(
                            text = "Favoriler",
                            style = MaterialTheme.typography.headlineLarge // Stil ekledik
                        )
                    }
                },
                colors = TopAppBarDefaults.smallTopAppBarColors(containerColor = Color.White)
            )
        },
        containerColor = Color.White,
        content = { paddingValues ->
            LazyColumn(
                contentPadding = PaddingValues(
                    start = 16.dp,
                    top = 16.dp + paddingValues.calculateTopPadding(),
                    end = 16.dp,
                    bottom = 16.dp + paddingValues.calculateBottomPadding()
                )
            ) {
                items(favoriListesi) { yemek ->
                    Card(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(vertical = 8.dp)
                            .border(1.dp, Color.Gray, shape = RoundedCornerShape(15.dp)),
                        colors = CardDefaults.cardColors(containerColor = Color.White)
                    ) {
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(8.dp)
                                .clip(MaterialTheme.shapes.large),
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            // Yemek Resmi
                            val imageUrl = "http://kasimadalan.pe.hu/yemekler/resimler/${yemek.yemek_resim}"

                            GlideImage(
                                imageModel = imageUrl,
                                contentDescription = yemek.yemek_adi,
                                modifier = Modifier
                                    .size(120.dp)
                                    .clip(MaterialTheme.shapes.medium)
                            )

                            // Yemek Bilgileri
                            Column(
                                modifier = Modifier
                                    .padding(start = 8.dp)
                                    .weight(1f)
                            ) {
                                Text(
                                    text = yemek.yemek_adi,
                                    style = MaterialTheme.typography.bodyLarge,
                                    modifier = Modifier.padding(vertical = 4.dp)
                                )
                                Text(
                                    text = "${yemek.yemek_fiyat}₺",
                                    style = MaterialTheme.typography.bodyMedium
                                )
                            }

                            // Silme Butonu En Sağa
                            IconButton(
                                onClick = {
                                    favoriSayfaViewModel.favoriSil(yemek.yemek_id)
                                }
                            ) {
                                Icon(
                                    Icons.Default.Delete,
                                    contentDescription = "Favoriden Sil",
                                    tint = Anarenk,
                                    modifier = Modifier.size(30.dp)
                                )
                            }
                        }
                    }
                }
            }
        }
    )


}

