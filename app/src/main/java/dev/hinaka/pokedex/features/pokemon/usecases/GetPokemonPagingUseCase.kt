package dev.hinaka.pokedex.features.pokemon.usecases

import dev.hinaka.pokedex.domain.repositories.PokemonRepository
import javax.inject.Inject

class GetPokemonPagingUseCase @Inject constructor(
  private val repository: PokemonRepository,
) {
  operator fun invoke(pageSize: Int) = repository.getPokemonPagingFlow(pageSize)
}