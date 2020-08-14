package com.aldidwikip.roomexamples.di

import android.content.Context
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import com.aldidwikip.roomexamples.data.room.WordDao
import com.aldidwikip.roomexamples.data.room.WordRoomDatabase
import com.aldidwikip.roomexamples.data.room.WordRoomDatabase.Companion.populateDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import javax.inject.Singleton

@Module
@InstallIn(ApplicationComponent::class)
object AppModule {

    private lateinit var database: WordRoomDatabase

    @Singleton
    @Provides
    fun provideDatabase(@ApplicationContext context: Context): WordRoomDatabase {
        database = Room.databaseBuilder(context, WordRoomDatabase::class.java, "word_database")
                .fallbackToDestructiveMigration()
                .addCallback(object : RoomDatabase.Callback() {
                    override fun onCreate(db: SupportSQLiteDatabase) {
                        super.onCreate(db)
                        GlobalScope.launch {
                            populateDatabase(database.wordDao())
                        }
                    }
                })
                .build()

        return database
    }

    @Singleton
    @Provides
    fun provideWordDao(database: WordRoomDatabase): WordDao = database.wordDao()
}