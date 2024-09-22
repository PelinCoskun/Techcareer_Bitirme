package com.example.yemeksiparisuygulamasi.data.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import org.jetbrains.annotations.NotNull

@Entity(tableName = "favoriler")
data class FavoriYemek(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "yemek_id") @NotNull var yemek_id: Int=0,
    @ColumnInfo(name = "yemek_adi") @NotNull var yemek_adi: String,
    @ColumnInfo(name = "yemek_resim") @NotNull var yemek_resim: String,
    @ColumnInfo(name = "yemek_fiyat") @NotNull var yemek_fiyat: Int,
) {
}