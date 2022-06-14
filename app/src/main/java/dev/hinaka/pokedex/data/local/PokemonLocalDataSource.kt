package dev.hinaka.pokedex.data.local

import androidx.paging.LoadType
import androidx.room.withTransaction
import javax.inject.Inject

class PokemonLocalDataSource @Inject constructor(
  private val db: PokedexDatabase,
) {
  private val pokemonDao = db.pokemonDao()

  suspend fun count() = db.withTransaction {
    pokemonDao.count()
  }

  suspend fun insertAll(loadType: LoadType, pokemons: List<PokemonEntity>) {
    db.withTransaction {
      if (loadType == LoadType.REFRESH) {
        pokemonDao.clearAll()
      }

      pokemonDao.insertAll(pokemons)
    }
  }

  fun pagingSource() = pokemonDao.pagingSource()
}