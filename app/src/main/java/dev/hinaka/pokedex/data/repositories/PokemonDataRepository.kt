package dev.hinaka.pokedex.data.repositories

import androidx.paging.ExperimentalPagingApi
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.map
import dev.hinaka.pokedex.data.local.PokemonLocalDataSource
import dev.hinaka.pokedex.data.local.toPokemon
import dev.hinaka.pokedex.data.remote.PokemonRemoteDataSource
import dev.hinaka.pokedex.domain.repositories.PokemonRepository
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class PokemonDataRepository @Inject constructor(
  private val pokemonLocalDataSource: PokemonLocalDataSource,
  private val pokemonRemoteDataSource: PokemonRemoteDataSource,
) : PokemonRepository {

  @OptIn(ExperimentalPagingApi::class)
  override fun getPokemonPagingFlow(pageSize: Int) = Pager(
    config = PagingConfig(pageSize),
    remoteMediator = PokemonRemoteMediator(pokemonLocalDataSource, pokemonRemoteDataSource)
  ) {
    pokemonLocalDataSource.pagingSource()
  }.flow.map { it.map { it.toPokemon() } }
}