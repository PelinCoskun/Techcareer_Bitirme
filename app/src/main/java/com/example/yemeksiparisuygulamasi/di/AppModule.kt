package com.example.yemeksiparisuygulamasi.di

import android.content.Context
import androidx.room.Room
import com.example.yemeksiparisuygulamasi.data.datasource.FavoriYemekDataSource
import com.example.yemeksiparisuygulamasi.data.datasource.YemeklerDataSource
import com.example.yemeksiparisuygulamasi.data.repo.FavoriYemekRepository
import com.example.yemeksiparisuygulamasi.data.repo.YemeklerRepository
import com.example.yemeksiparisuygulamasi.retrofit.ApiUtils
import com.example.yemeksiparisuygulamasi.retrofit.YemeklerDao
import com.example.yemeksiparisuygulamasi.room.FavoriYemekDao
import com.example.yemeksiparisuygulamasi.room.Veritabani
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
class AppModule {
    @Provides
    @Singleton
    fun provideYemeklerRepository(yds: YemeklerDataSource): YemeklerRepository {
        return   YemeklerRepository(yds)
    }

    @Provides
    @Singleton
    fun provideYemeklerDataSource(ydao:YemeklerDao): YemeklerDataSource {
        return YemeklerDataSource(ydao)
    }

    @Provides
    @Singleton
    fun provideYemeklerDao(): YemeklerDao {
        return ApiUtils.getYemeklerDao()
    }

    @Provides
    @Singleton
    fun provideFavoriYemekRepository(fds: FavoriYemekDataSource): FavoriYemekRepository {
        return   FavoriYemekRepository(fds)
    }

    @Provides
    @Singleton
    fun provideFavoriYemekDataSource(fdao: FavoriYemekDao): FavoriYemekDataSource {
        return FavoriYemekDataSource(fdao)
    }

    @Provides
    @Singleton
    fun provideFavoriYemekDao(@ApplicationContext context: Context): FavoriYemekDao {
        val vt = Room.databaseBuilder(context, Veritabani::class.java,"favoriler.sqlite")
            .createFromAsset("favoriler.sqlite").build()
        return vt.getFavoriYemekDao()
    }

}