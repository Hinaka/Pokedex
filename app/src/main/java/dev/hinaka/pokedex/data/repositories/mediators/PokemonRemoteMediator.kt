package dev.hinaka.pokedex.data.repositories.mediators

import androidx.paging.ExperimentalPagingApi
import androidx.paging.LoadType
import androidx.paging.PagingState
import androidx.paging.RemoteMediator
import androidx.room.withTransaction
import dev.hinaka.pokedex.data.local.PokedexDatabase
import dev.hinaka.pokedex.data.local.entities.PokemonEntity
import dev.hinaka.pokedex.data.remote.services.PokemonService
import dev.hinaka.pokedex.data.repositories.mappers.toEntity
import kotlinx.coroutines.async
import kotlinx.coroutines.awaitAll
import kotlinx.coroutines.coroutineScope
import javax.inject.Inject

@OptIn(ExperimentalPagingApi::class)
class PokemonRemoteMediator @Inject constructor(
  private val database: PokedexDatabase,
  private val pokemonService: PokemonService,
) : RemoteMediator<Int, PokemonEntity>() {

  private val pokemonDao = database.pokemonDao()

  override suspend fun initialize(): InitializeAction {
    return InitializeAction.SKIP_INITIAL_REFRESH
  }

  override suspend fun load(
    loadType: LoadType,
    state: PagingState<Int, PokemonEntity>,
  ): MediatorResult {
    return try {
      val offset = when (loadType) {
        LoadType.REFRESH -> 0
        LoadType.PREPEND ->
          return MediatorResult.Success(endOfPaginationReached = true)
        LoadType.APPEND -> pokemonDao.count()
      }
      val limit = state.config.pageSize

      val pokemons = getPokemons(offset, limit)
      val entities = pokemons.map { it.toEntity() }

      database.withTransaction {
        if (loadType == LoadType.REFRESH) {
          pokemonDao.clearAll()
        }

        pokemonDao.insertAll(entities)
      }

      MediatorResult.Success(endOfPaginationReached = pokemons.isEmpty())
    } catch (e: Exception) {
      MediatorResult.Error(e)
    }
  }

  private suspend fun getPokemons(offset: Int, limit: Int) = coroutineScope {
    val getPokemonsResponse = pokemonService.getPokemons(offset, limit)
    getPokemonsResponse.results.orEmpty().map {
      async { pokemonService.getPokemon(it.name.orEmpty()) }
    }.awaitAll()
  }
}