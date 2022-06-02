package dev.hinaka.pokedex.data.remote

import dev.hinaka.pokedex.data.responses.pokemon.toPokemons

class PokemonRemoteDataSource(
  private val pokemonService: PokemonService
) {
  suspend fun getPokemons() = pokemonService.getPokemons().toPokemons()
}