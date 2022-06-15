package dev.hinaka.pokedex.data.remote

import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class RemoteModule {

  @OptIn(ExperimentalSerializationApi::class)
  @Singleton
  @Provides
  fun provideRetrofit(): Retrofit {
    val contentType = "application/json".toMediaType()
    val json = Json {
      ignoreUnknownKeys = true
    }
    val jsonConverterFactory = json.asConverterFactory(contentType)

    return Retrofit.Builder()
      .baseUrl("https://pokeapi.co/api/v2/")
      .addConverterFactory(jsonConverterFactory)
      .build()
  }

  @Provides
  fun providePokemonService(retrofit: Retrofit): PokemonService {
    return retrofit.create(PokemonService::class.java)
  }
}