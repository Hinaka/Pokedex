package dev.hinaka.pokedex.domain.repositories

import androidx.paging.PagingData
import dev.hinaka.pokedex.domain.models.pokemon.Pokemon
import kotlinx.coroutines.flow.Flow

interface PokemonRepository {
  fun getPokemonPagingFlow(pageSize: Int): Flow<PagingData<Pokemon>>
}