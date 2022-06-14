package dev.hinaka.pokedex.data.remote

import androidx.paging.PagingSource
import androidx.paging.PagingState
import dev.hinaka.pokedex.data.responses.pokemon.toPokemon
import dev.hinaka.pokedex.domain.models.pokemon.Pokemon
import kotlinx.coroutines.async
import kotlinx.coroutines.awaitAll
import kotlinx.coroutines.coroutineScope
import javax.inject.Inject

class PokemonPagingSource @Inject constructor(
  private val service: PokemonService,
) : PagingSource<Int, Pokemon>() {

  override fun getRefreshKey(state: PagingState<Int, Pokemon>): Int? {
    return null
  }

  override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Pokemon> =
    coroutineScope {
      try {
        val offset = params.key ?: 0
        val limit = params.loadSize
        val response = service.getPokemons(offset, limit)
        val pokemons = response.results.orEmpty().map {
          async { service.getPokemon(it.name.orEmpty()).toPokemon() }
        }
        LoadResult.Page(
          data = pokemons.awaitAll().filterNotNull(),
          prevKey = null,
          nextKey = offset + limit,
        )
      } catch (e: Exception) {
        LoadResult.Error(e)
      }
    }
}