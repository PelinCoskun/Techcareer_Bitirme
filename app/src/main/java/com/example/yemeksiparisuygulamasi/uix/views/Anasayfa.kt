package com.example.yemeksiparisuygulamasi.uix.views

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
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
import com.example.yemeksiparisuygulamasi.ui.theme.Anarenk3
import com.example.yemeksiparisuygulamasi.ui.theme.Anarenk4
import com.example.yemeksiparisuygulamasi.uix.viewmodel.AnasayfaViewModel
import com.skydoves.landscapist.glide.GlideImage
import androidx.compose.foundation.focusable
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.IconButton
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.focus.focusModifier
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.focus.FocusManager
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.focus.focusModifier
import androidx.compose.ui.platform.LocalFocusManager
import com.example.yemeksiparisuygulamasi.data.entity.Yemekler
import com.example.yemeksiparisuygulamasi.ui.theme.Anarenk2
import com.example.yemeksiparisuygulamasi.uix.viewmodel.FavoriSayfaViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Anasayfa(navController: NavController, anasayfaViewModel: AnasayfaViewModel, favoriSayfaViewModel: FavoriSayfaViewModel) {

    val favoriListesi = favoriSayfaViewModel.favoriListesi.observeAsState(listOf()).value

    val aramaSorgusu = remember { mutableStateOf("") }
    val isFocused = remember { mutableStateOf(false) }
    val yemeklerListesi = anasayfaViewModel.yemeklerListesi.observeAsState(listOf())
    val filteredYemeklerListesi = yemeklerListesi.value.filter {
        it.yemek_adi.contains(aramaSorgusu.value, ignoreCase = true)
    }

    val focusRequester = remember { FocusRequester() }

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 10.dp)
                    ) {
                        TextField(
                            value = aramaSorgusu.value,
                            onValueChange = { newQuery -> aramaSorgusu.value = newQuery },
                            modifier = Modifier
                                .fillMaxWidth()
                                .focusRequester(focusRequester)
                                .onFocusChanged { focusState -> isFocused.value = focusState.isFocused }
                                .background(Color.Transparent, shape = RoundedCornerShape(15.dp))
                                .border(1.dp, Anarenk2, shape = RoundedCornerShape(30.dp)),
                            label = { Text(text = if (isFocused.value) "Ara" else "Yemekler") },
                            colors = TextFieldDefaults.textFieldColors(
                                containerColor = Color.Transparent,
                                focusedLabelColor = Color.Black,
                                unfocusedLabelColor = Color.Black,
                                unfocusedIndicatorColor = Color.Transparent,
                                focusedIndicatorColor = Color.Transparent

                            ),
                            trailingIcon = {
                                if (aramaSorgusu.value.isNotEmpty()) {
                                    Icon(
                                        imageVector = Icons.Default.Close,
                                        contentDescription = "Kapat",
                                        modifier = Modifier.clickable { aramaSorgusu.value = "" }
                                    )
                                } else {
                                    Icon(
                                        imageVector = Icons.Default.Search,
                                        contentDescription = "Ara"
                                    )
                                }
                            }
                        )
                    }
                },
                navigationIcon = {
                    Icon(
                        painter = painterResource(id = R.drawable.logo),
                        contentDescription = "Logo",
                        modifier = Modifier.padding(start = 8.dp)
                            .size(40.dp),
                        tint = Anarenk
                    )
                },
                colors = TopAppBarDefaults.smallTopAppBarColors(containerColor = Color.White)
            )
        },
        containerColor = Color.White,
        content = { paddingValues ->

            LazyVerticalGrid(
                columns = GridCells.Fixed(2),
                contentPadding = PaddingValues(
                    start = 16.dp,
                    top = 16.dp + paddingValues.calculateTopPadding(),
                    end = 16.dp,
                    bottom = 16.dp + paddingValues.calculateBottomPadding()
                ),
                modifier = Modifier.fillMaxWidth()
            ) {
                items(filteredYemeklerListesi) { yemek ->
                    val isFavorite = remember { mutableStateOf(favoriListesi.any { it.yemek_adi == yemek.yemek_adi }) }

                    Box(
                        modifier = Modifier
                            .padding(8.dp)
                            .fillMaxWidth(0.9f)
                            .clickable {
                                navController.navigate("yemekDetaySayfa/${yemek.yemek_id}/${yemek.yemek_adi}/${yemek.yemek_fiyat}/${yemek.yemek_resim_adi}")
                            }
                    ) {
                        Card(
                            modifier = Modifier.fillMaxWidth()
                                .fillMaxWidth()
                                .border(1.dp, Anarenk2, shape = RoundedCornerShape(15.dp)),
                            colors = CardDefaults.cardColors(containerColor = Color.White)
                        ) {
                            Column(
                                horizontalAlignment = Alignment.CenterHorizontally,
                                modifier = Modifier.padding(8.dp)
                            ) {
                                val imageUrl = "http://kasimadalan.pe.hu/yemekler/resimler/${yemek.yemek_resim_adi}"
                                GlideImage(
                                    imageModel = imageUrl,
                                    modifier = Modifier.size(180.dp).clip(RoundedCornerShape(12.dp))
                                )
                                Text(text = yemek.yemek_adi, style = MaterialTheme.typography.bodyLarge)
                                Text(text = "${yemek.yemek_fiyat}₺", style = MaterialTheme.typography.bodyMedium)
                            }
                        }

                        IconButton(
                            onClick = {
                                if (isFavorite.value) {
                                    favoriSayfaViewModel.favoriSilByYemekAdi(yemek.yemek_adi)
                                } else {
                                    favoriSayfaViewModel.favoriEkle(yemek.yemek_adi, yemek.yemek_resim_adi, yemek.yemek_fiyat)
                                }
                                isFavorite.value = !isFavorite.value
                            },
                            modifier = Modifier.align(Alignment.TopEnd).padding(8.dp)
                        ) {
                            Icon(
                                painter = painterResource(id = if (isFavorite.value) R.drawable.kirmizi else R.drawable.favori),
                                contentDescription = if (isFavorite.value) "Favori" else "Favori değil",
                                tint = if (isFavorite.value) Color.Red else Color.Gray
                            )
                        }
                    }
                }
            }
        }

    )
}




