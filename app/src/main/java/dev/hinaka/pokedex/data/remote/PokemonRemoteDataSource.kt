package dev.hinaka.pokedex.data.remote

import dev.hinaka.pokedex.data.responses.pokemon.toPokemon
import dev.hinaka.pokedex.domain.models.pokemon.Pokemon
import kotlinx.coroutines.async
import kotlinx.coroutines.awaitAll
import kotlinx.coroutines.coroutineScope
import javax.inject.Inject

class PokemonRemoteDataSource @Inject constructor(
  private val pokemonService: PokemonService,
) {
  suspend fun getPokemons(offset: Int, limit: Int): List<Pokemon> = coroutineScope {
    val getPokemonsResponse = pokemonService.getPokemons(offset, limit)
    val pokemons = getPokemonsResponse.results.orEmpty().map {
      async { pokemonService.getPokemon(it.name.orEmpty()).toPokemon() }
    }

    pokemons.awaitAll().filterNotNull()
  }
}