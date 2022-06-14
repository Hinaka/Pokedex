package dev.hinaka.pokedex.data.repositories

import androidx.paging.Pager
import androidx.paging.PagingConfig
import dev.hinaka.pokedex.data.repositories.paging.PokemonPagingSource
import dev.hinaka.pokedex.data.remote.PokemonRemoteDataSource
import dev.hinaka.pokedex.domain.repositories.PokemonRepository
import javax.inject.Inject

class PokemonDataRepository @Inject constructor(
  private val pokemonRemoteDataSource: PokemonRemoteDataSource,
) : PokemonRepository {

  override fun getPokemonPagingFlow(pageSize: Int) = Pager(
    config = PagingConfig(pageSize)
  ) {
    PokemonPagingSource(pokemonRemoteDataSource)
  }.flow
}