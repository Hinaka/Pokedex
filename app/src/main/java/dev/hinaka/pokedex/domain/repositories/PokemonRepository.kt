package dev.hinaka.pokedex.domain.repositories

import dev.hinaka.pokedex.domain.models.pokemon.Pokemon

interface PokemonRepository {
  suspend fun getPokemons(): List<Pokemon>
}