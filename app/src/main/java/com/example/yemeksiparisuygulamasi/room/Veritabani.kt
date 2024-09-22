package com.example.yemeksiparisuygulamasi.room

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.yemeksiparisuygulamasi.data.entity.FavoriYemek

@Database(entities = [FavoriYemek::class], version = 1)
abstract class Veritabani : RoomDatabase() {
    abstract fun getFavoriYemekDao() : FavoriYemekDao
}