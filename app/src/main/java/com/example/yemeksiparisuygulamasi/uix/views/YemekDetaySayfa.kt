package com.example.yemeksiparisuygulamasi.uix.views

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
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.yemeksiparisuygulamasi.R
import com.example.yemeksiparisuygulamasi.data.entity.Yemekler
import com.example.yemeksiparisuygulamasi.ui.theme.Anarenk
import com.example.yemeksiparisuygulamasi.ui.theme.Anarenk4
import com.example.yemeksiparisuygulamasi.uix.viewmodel.YemekDetaySayfaViewModel
import com.skydoves.landscapist.glide.GlideImage
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.MutableState
import com.example.yemeksiparisuygulamasi.ui.theme.Anarenk2
import com.example.yemeksiparisuygulamasi.ui.theme.Anarenk3


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun YemekDetaySayfa(
    navController: NavController,
    yemekDetaySayfaViewModel: YemekDetaySayfaViewModel,
    yemek: Yemekler,
    secilenItem: MutableState<Int>
) {
    val quantity = remember { mutableStateOf("1") }
    val totalPrice = yemekDetaySayfaViewModel.toplamGuncelle(quantity.value, yemek.yemek_fiyat.toInt())

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Box(
                        modifier = Modifier.fillMaxWidth(),
                        contentAlignment = Alignment.Center // Ortala
                    ) {
                        Text(
                            text = "Yemek Detayı",
                            style = MaterialTheme.typography.headlineLarge // Stil ekledik
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
                    .padding(paddingValues)
                    .padding(16.dp),
                verticalArrangement = Arrangement.SpaceBetween,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                // Card with food details
                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .border(1.dp, Anarenk2, shape = RoundedCornerShape(15.dp)),
                    shape = RoundedCornerShape(15.dp),
                    colors = CardDefaults.cardColors(containerColor = Color.White)
                ) {
                    Column(
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.spacedBy(16.dp),
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(16.dp)
                    ) {
                        // Image of the food
                        GlideImage(
                            imageModel = "http://kasimadalan.pe.hu/yemekler/resimler/${yemek.yemek_resim_adi}",
                            modifier = Modifier
                                .size(200.dp)
                                .clip(RoundedCornerShape(8.dp))
                        )

                        // Name of the food
                        Text(
                            text = yemek.yemek_adi ?: "Yemek Adı Yok",
                            style = MaterialTheme.typography.headlineMedium,
                            modifier = Modifier.padding(bottom = 8.dp)
                        )

                        // Price of the food
                        Text(
                            text = "${yemek.yemek_fiyat}₺",
                            style = MaterialTheme.typography.titleMedium
                        )

                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.spacedBy(16.dp)
                        ) {
                            Box(
                                modifier = Modifier
                                    .background(Color.Transparent, shape = RoundedCornerShape(15.dp))
                                    .border(1.dp, Anarenk2, shape = RoundedCornerShape(15.dp))
                                    .size(30.dp)
                            ) {
                                IconButton(onClick = {
                                    quantity.value = yemekDetaySayfaViewModel.miktarAzalt(quantity.value).toString()
                                    quantity.value = yemekDetaySayfaViewModel.miktarKontrol(quantity.value)
                                }) {
                                    Icon(painter = painterResource(id = R.drawable.azalt), contentDescription = "Azalt")
                                }
                            }

                            Text(text = quantity.value)

                            Box(
                                modifier = Modifier
                                    .background(Color.Transparent, shape = RoundedCornerShape(15.dp))
                                    .border(1.dp, Anarenk2, shape = RoundedCornerShape(15.dp))
                                    .size(30.dp)
                            ) {
                                IconButton(onClick = {
                                    quantity.value = yemekDetaySayfaViewModel.miktarArttir(quantity.value).toString()
                                    quantity.value = yemekDetaySayfaViewModel.miktarKontrol(quantity.value)
                                }) {
                                    Icon(painter = painterResource(id = R.drawable.ekle), contentDescription = "Ekle")
                                }
                            }
                        }

                        Text(
                            text = "Toplam: ${totalPrice}₺",
                            style = MaterialTheme.typography.titleMedium
                        )
                    }
                }

                // Sepete Ekle Button (Card altında)
                Button(
                    onClick = {
                        yemekDetaySayfaViewModel.sepeteEkle(
                            foodName = yemek.yemek_adi,
                            foodImageName = yemek.yemek_resim_adi,
                            foodPrice = yemek.yemek_fiyat.toInt(),
                            foodQuantity = quantity.value.toInt(),
                            userName = yemekDetaySayfaViewModel.userName
                        )
                        secilenItem.value = 2
                        navController.navigate("sepetSayfa") { launchSingleTop = true }
                    },
                    modifier = Modifier
                        .fillMaxWidth(0.5f) // Buton genişliği
                        .padding(vertical = 10.dp)
                        .height(80.dp),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Anarenk
                    )
                ) {
                    Text(text = "Sepete Ekle")
                }
            }
        }
    )
}




