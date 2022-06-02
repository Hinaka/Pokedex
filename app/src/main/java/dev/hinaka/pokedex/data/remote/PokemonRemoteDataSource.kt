package dev.hinaka.pokedex.data.remote

import dev.hinaka.pokedex.data.responses.pokemon.toPokemons
import javax.inject.Inject

class PokemonRemoteDataSource @Inject constructor(
  private val pokemonService: PokemonService
) {
  suspend fun getPokemons() = pokemonService.getPokemons().toPokemons()
}