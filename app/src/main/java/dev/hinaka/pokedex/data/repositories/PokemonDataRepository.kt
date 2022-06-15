package dev.hinaka.pokedex.data.repositories

import androidx.paging.ExperimentalPagingApi
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.map
import dev.hinaka.pokedex.data.local.PokedexDatabase
import dev.hinaka.pokedex.data.local.entities.toPokemon
import dev.hinaka.pokedex.data.remote.services.PokemonService
import dev.hinaka.pokedex.domain.repositories.PokemonRepository
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class PokemonDataRepository @Inject constructor(
  private val database: PokedexDatabase,
  private val pokemonService: PokemonService,
) : PokemonRepository {

  @OptIn(ExperimentalPagingApi::class)
  override fun getPokemonPagingFlow(pageSize: Int) = Pager(
    config = PagingConfig(pageSize),
    remoteMediator = PokemonRemoteMediator(database, pokemonService)
  ) {
    database.pokemonDao().pagingSource()
  }.flow.map {
    it.map { it.toPokemon() }
  }
}