package dev.hinaka.pokedex.data.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(
  entities = [PokemonEntity::class],
  version = 1,
  exportSchema = false,
)
abstract class PokedexDatabase : RoomDatabase() {

  companion object {
    fun create(context: Context, useInMemory: Boolean): PokedexDatabase {
      val builder = if (useInMemory) {
        Room.inMemoryDatabaseBuilder(context, PokedexDatabase::class.java)
      } else {
        Room.databaseBuilder(context, PokedexDatabase::class.java, "pokedex.db")
      }
      return builder
        .fallbackToDestructiveMigration()
        .build()
    }
  }
}