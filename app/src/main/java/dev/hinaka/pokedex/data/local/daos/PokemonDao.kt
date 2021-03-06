package dev.hinaka.pokedex.data.local.daos

import androidx.paging.PagingSource
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import dev.hinaka.pokedex.data.local.entities.PokemonEntity

@Dao
interface PokemonDao {

  @Insert(onConflict = OnConflictStrategy.REPLACE)
  suspend fun insertAll(pokemons: List<PokemonEntity>)

  @Query("SELECT * FROM pokemons")
  fun pagingSource(): PagingSource<Int, PokemonEntity>

  @Query("DELETE FROM pokemons")
  suspend fun clearAll()

  @Query("SELECT COUNT(*) FROM pokemons")
  suspend fun count(): Int
}