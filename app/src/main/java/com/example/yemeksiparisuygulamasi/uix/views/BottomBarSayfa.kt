package com.example.yemeksiparisuygulamasi.uix.views

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.yemeksiparisuygulamasi.R
import com.example.yemeksiparisuygulamasi.ui.theme.Anarenk
import com.example.yemeksiparisuygulamasi.uix.viewmodel.AnasayfaViewModel
import com.example.yemeksiparisuygulamasi.uix.viewmodel.FavoriSayfaViewModel
import com.example.yemeksiparisuygulamasi.uix.viewmodel.SepetSayfaViewModel
import com.example.yemeksiparisuygulamasi.uix.viewmodel.YemekDetaySayfaViewModel

@Composable
fun BottomBarSayfa(
    navController: NavController,
    secilenItem: MutableState<Int>
) {

    BottomAppBar(
        modifier = Modifier.height(80.dp),
        containerColor = Color.White,
        contentColor = Color.White,
        tonalElevation = 40.dp,
        content = {
            NavigationBarItem(
                selected = secilenItem.value == 0,
                onClick = {
                    secilenItem.value = 0
                    navController.navigate("anasayfa") { launchSingleTop = true }
                },
                icon = {
                    Icon(
                        painter = painterResource(id = R.drawable.logo),
                        contentDescription = "",
                        modifier = Modifier.size(24.dp),
                        tint = Anarenk
                    )
                },
                label = { Text(text = "Anasayfa", color = Color.Gray) }
            )
            NavigationBarItem(
                selected = secilenItem.value == 1,
                onClick = {
                    secilenItem.value = 1
                    navController.navigate("favoriler")
                },
                icon = {
                    Icon(
                        painter = painterResource(id = R.drawable.favori),
                        contentDescription = "",
                        modifier = Modifier.size(22.dp)
                    )
                },
                label = { Text(text = "Favoriler", color = Color.Gray) }
            )
            NavigationBarItem(
                selected = secilenItem.value == 2,
                onClick = {
                    secilenItem.value = 2
                    navController.navigate("sepetSayfa")
                },
                icon = {
                    Icon(
                        painter = painterResource(id = R.drawable.sepet),
                        contentDescription = "",
                        modifier = Modifier.size(22.dp)
                    )
                },
                label = { Text(text = "Sepetim", color = Color.Gray) }
            )
        }
    )
}


