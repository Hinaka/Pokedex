package dev.hinaka.pokedex.ui.pokedex.usecases

import dev.hinaka.pokedex.domain.models.pokemon.Pokemon
import dev.hinaka.pokedex.domain.repositories.PokemonRepository

class GetPokemonsUseCase(
  private val repository: PokemonRepository,
) {
  suspend operator fun invoke(): List<Pokemon> {
    return repository.getPokemons()
  }
}