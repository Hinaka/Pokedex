package dev.hinaka.pokedex.ui.pokedex

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityRetainedComponent
import dev.hinaka.pokedex.data.repositories.PokemonDataRepository
import dev.hinaka.pokedex.domain.repositories.PokemonRepository

@Module
@InstallIn(ActivityRetainedComponent::class)
abstract class PokemonModule {
  @Binds
  abstract fun bindPokemonRepository(repository: PokemonDataRepository): PokemonRepository
}