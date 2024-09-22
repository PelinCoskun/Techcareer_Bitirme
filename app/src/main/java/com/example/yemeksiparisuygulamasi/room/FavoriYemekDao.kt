package com.example.yemeksiparisuygulamasi.room

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.example.yemeksiparisuygulamasi.data.entity.FavoriYemek

@Dao
interface FavoriYemekDao {

    @Query("SELECT * FROM favoriler WHERE yemek_adi = :yemek_adi LIMIT 1")
    suspend fun getFavoriYemekByName(yemek_adi: String): FavoriYemek?

    @Insert
    suspend fun favoriEkle(favoriYemek: FavoriYemek)

    @Delete
    suspend fun favoriSil(favoriYemek: FavoriYemek)

    @Query("DELETE FROM favoriler WHERE yemek_adi = :yemek_adi")
    suspend fun favoriSilByYemekAdi(yemek_adi: String)

    @Query("SELECT * FROM favoriler")
   suspend fun tumFavoriler(): List<FavoriYemek>

}