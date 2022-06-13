package dev.hinaka.pokedex.features.pokemon.usecases

import dev.hinaka.pokedex.domain.models.pokemon.Pokemon
import dev.hinaka.pokedex.domain.repositories.PokemonRepository
import javax.inject.Inject

class GetPokemonsUseCase @Inject constructor(
  private val repository: PokemonRepository,
) {
  suspend operator fun invoke(): List<Pokemon> {
    return repository.getPokemons()
  }
}