package com.akichiy.rickandmortyapp.di

import android.content.Context
import androidx.room.Room
import com.akichiy.rickandmortyapp.data.local.CharacterDao
import com.akichiy.rickandmortyapp.data.local.CharacterDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Provides
    @Singleton
    fun provideDatabase(@ApplicationContext context: Context): CharacterDatabase =
        Room.databaseBuilder(
            context,
            CharacterDatabase::class.java,
            "character_db"
        ).build()

    @Provides
    fun provideCharacterDao(database: CharacterDatabase): CharacterDao =
        database.characterDao()
}