package dev.hinaka.pokedex.features.pokemon.ui.list

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.cachedIn
import dagger.hilt.android.lifecycle.HiltViewModel
import dev.hinaka.pokedex.data.remote.PokemonPagingSource
import dev.hinaka.pokedex.data.remote.PokemonService
import dev.hinaka.pokedex.domain.models.pokemon.Pokemon
import dev.hinaka.pokedex.features.pokemon.usecases.GetPokemonsUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PokemonListViewModel @Inject constructor(
  private val getPokemonsUseCase: GetPokemonsUseCase,
  private val service: PokemonService,
) : ViewModel() {
  val pokemons = MutableStateFlow<List<Pokemon>>(emptyList())

  val flow = Pager(
    PagingConfig(pageSize = 20)
  ) {
    PokemonPagingSource(service)
  }.flow.cachedIn(viewModelScope)

  init {
    viewModelScope.launch {
      val newPokemons = getPokemonsUseCase()
      pokemons.update {
        newPokemons
      }
    }
  }
}