package dev.hinaka.pokedex.data.repositories

import androidx.paging.ExperimentalPagingApi
import androidx.paging.LoadType
import androidx.paging.PagingState
import androidx.paging.RemoteMediator
import dev.hinaka.pokedex.data.local.PokemonEntity
import dev.hinaka.pokedex.data.local.PokemonLocalDataSource
import dev.hinaka.pokedex.data.local.toEntity
import dev.hinaka.pokedex.data.remote.PokemonRemoteDataSource
import dev.hinaka.pokedex.domain.models.pokemon.Pokemon
import javax.inject.Inject

@OptIn(ExperimentalPagingApi::class)
class PokemonRemoteMediator @Inject constructor(
  private val localSource: PokemonLocalDataSource,
  private val remoteSource: PokemonRemoteDataSource,
) : RemoteMediator<Int, PokemonEntity>() {

  override suspend fun initialize(): InitializeAction {
    return InitializeAction.SKIP_INITIAL_REFRESH
  }

  override suspend fun load(loadType: LoadType, state: PagingState<Int, PokemonEntity>): MediatorResult {
    return try {
      val offset = when (loadType) {
        LoadType.REFRESH -> 0
        LoadType.PREPEND ->
          return MediatorResult.Success(endOfPaginationReached = true)
        LoadType.APPEND -> localSource.count()
      }
      val limit = state.config.pageSize

      val pokemons = remoteSource.getPokemons(offset, limit)
      val entities = pokemons.map { it.toEntity() }

      localSource.insertAll(loadType, entities)

      MediatorResult.Success(endOfPaginationReached = pokemons.isEmpty())
    } catch (e: Exception) {
      MediatorResult.Error(e)
    }
  }
}