package dev.hinaka.pokedex.data.repositories

import androidx.paging.ExperimentalPagingApi
import androidx.paging.LoadType
import androidx.paging.PagingState
import androidx.paging.RemoteMediator
import androidx.room.withTransaction
import dev.hinaka.pokedex.data.local.PokedexDatabase
import dev.hinaka.pokedex.data.local.PokemonEntity
import dev.hinaka.pokedex.data.remote.PokemonService
import dev.hinaka.pokedex.data.responses.pokemon.GetPokemonResponse
import dev.hinaka.pokedex.domain.models.type.Type
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

private fun GetPokemonResponse.toEntity() = PokemonEntity(
  id = id?.toLong() ?: 0,
  name = name.orEmpty(),
  type1 = types?.firstOrNull()?.let {
    when (it.type?.name) {
      "normal" -> Type.NORMAL
      "fighting" -> Type.FIGHTING
      "flying" -> Type.FLYING
      "poison" -> Type.POISON
      "ground" -> Type.GROUND
      "rock" -> Type.ROCK
      "bug" -> Type.BUG
      "ghost" -> Type.GHOST
      "steel" -> Type.STEEL
      "fire" -> Type.FIRE
      "water" -> Type.WATER
      "grass" -> Type.GRASS
      "electric" -> Type.ELECTRIC
      "psychic" -> Type.PSYCHIC
      "ice" -> Type.ICE
      "dragon" -> Type.DRAGON
      "dark" -> Type.DARK
      "fairy" -> Type.FAIRY
      "unknown" -> Type.UNKNOWN
      else -> null
    }
  }?.ordinal ?: -1,
  type2 = types?.getOrNull(1)?.let {
    when (it.type?.name) {
      "normal" -> Type.NORMAL
      "fighting" -> Type.FIGHTING
      "flying" -> Type.FLYING
      "poison" -> Type.POISON
      "ground" -> Type.GROUND
      "rock" -> Type.ROCK
      "bug" -> Type.BUG
      "ghost" -> Type.GHOST
      "steel" -> Type.STEEL
      "fire" -> Type.FIRE
      "water" -> Type.WATER
      "grass" -> Type.GRASS
      "electric" -> Type.ELECTRIC
      "psychic" -> Type.PSYCHIC
      "ice" -> Type.ICE
      "dragon" -> Type.DRAGON
      "dark" -> Type.DARK
      "fairy" -> Type.FAIRY
      "unknown" -> Type.UNKNOWN
      else -> null
    }
  }?.ordinal ?: -1,
  imageUrl = sprites?.other?.officialArtwork?.front_default.orEmpty(),
)