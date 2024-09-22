package com.example.yemeksiparisuygulamasi.uix.views

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.yemeksiparisuygulamasi.data.entity.Yemekler
import com.example.yemeksiparisuygulamasi.uix.viewmodel.AnasayfaViewModel
import com.example.yemeksiparisuygulamasi.uix.viewmodel.FavoriSayfaViewModel
import com.example.yemeksiparisuygulamasi.uix.viewmodel.SepetSayfaViewModel
import com.example.yemeksiparisuygulamasi.uix.viewmodel.YemekDetaySayfaViewModel
import com.google.gson.Gson
@Composable
fun SayfaGecisleri(
    anasayfaViewModel: AnasayfaViewModel,
    sepetSayfaViewModel: SepetSayfaViewModel,
    yemekDetaySayfaViewModel: YemekDetaySayfaViewModel,
    favoriSayfaViewModel: FavoriSayfaViewModel
) {
    val navController = rememberNavController()
    val secilenItem= remember { mutableStateOf(0) }

    Scaffold(
        bottomBar = {
            BottomBarSayfa(
                navController = navController,
              secilenItem = secilenItem
            )
        }
    ) { paddingValues ->
        NavHost(
            navController = navController,
            startDestination = "anasayfa",
            modifier = Modifier.padding(paddingValues)
        ) {
            composable("anasayfa") {
                Anasayfa(navController, anasayfaViewModel, favoriSayfaViewModel)
            }
            composable("favoriler") {
                FavorilerSayfa(navController, favoriSayfaViewModel, anasayfaViewModel,secilenItem)
            }
            composable("sepetSayfa") {
                SepetSayfa(navController, sepetSayfaViewModel,secilenItem)
            }

            composable(
                "yemekDetaySayfa/{id}/{name}/{price}/{image}",
                arguments = listOf(
                    navArgument("id") { type = NavType.IntType },
                    navArgument("name") { type = NavType.StringType },
                    navArgument("price") { type = NavType.IntType },
                    navArgument("image") { type = NavType.StringType }
                )
            ) { backStackEntry ->
                val id = backStackEntry.arguments?.getInt("id")
                val name = backStackEntry.arguments?.getString("name")
                val price = backStackEntry.arguments?.getInt("price")
                val image = backStackEntry.arguments?.getString("image")

                val yemek = Yemekler(id!!, name!!, image!!, price!!)
                YemekDetaySayfa(navController, yemekDetaySayfaViewModel, yemek,secilenItem)
            }
        }
    }
}
