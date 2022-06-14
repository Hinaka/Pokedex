package dev.hinaka.pokedex.data.repositories.paging

import androidx.paging.PagingSource
import androidx.paging.PagingState
import dev.hinaka.pokedex.data.remote.PokemonRemoteDataSource
import dev.hinaka.pokedex.domain.models.pokemon.Pokemon
import kotlinx.coroutines.coroutineScope
import javax.inject.Inject

class PokemonPagingSource @Inject constructor(
  private val remote: PokemonRemoteDataSource,
) : PagingSource<Int, Pokemon>() {

  override fun getRefreshKey(state: PagingState<Int, Pokemon>): Int? {
    return null
  }

  override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Pokemon> =
    coroutineScope {
      try {
        val offset = params.key ?: 0
        val limit = params.loadSize
        val pokemons = remote.getPokemons(offset, limit)
        LoadResult.Page(
          data = pokemons,
          prevKey = null,
          nextKey = offset + limit,
        )
      } catch (e: Exception) {
        LoadResult.Error(e)
      }
    }
}