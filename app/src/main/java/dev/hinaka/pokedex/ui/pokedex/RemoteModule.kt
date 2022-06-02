package dev.hinaka.pokedex.ui.pokedex

import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import dev.hinaka.pokedex.data.remote.PokemonService
import kotlinx.serialization.json.Json
import okhttp3.MediaType
import retrofit2.Retrofit

@Module
@InstallIn(SingletonComponent::class)
object RemoteModule {
  @Provides
  fun providePokemonService(): PokemonService {
    val contentType = MediaType.parse("application/json")
    val json = Json {
      ignoreUnknownKeys = true
    }
    val retrofit = Retrofit.Builder()
      .baseUrl("https://pokeapi.co/api/v2/")
      .addConverterFactory(json.asConverterFactory(contentType!!))
      .build()

    return retrofit.create(PokemonService::class.java)
  }
}