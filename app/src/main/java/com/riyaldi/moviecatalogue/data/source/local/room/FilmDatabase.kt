package com.riyaldi.moviecatalogue.data.source.local.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.riyaldi.moviecatalogue.data.source.local.entity.MovieEntity
import com.riyaldi.moviecatalogue.data.source.local.entity.TvShowEntity

@Database(
        entities = [MovieEntity::class, TvShowEntity::class],
        version = 1,
        exportSchema = false
)
abstract class FilmDatabase : RoomDatabase() {
    abstract fun filmDao(): FilmDao

    companion object {
        @Volatile
        private var INSTANCE: FilmDatabase? = null

        fun getInstance(context: Context): FilmDatabase =
                INSTANCE ?: synchronized(this) {
                    INSTANCE
                            ?: Room.databaseBuilder(context.applicationContext, FilmDatabase::class.java, "Film.db").build()
                }
    }
}