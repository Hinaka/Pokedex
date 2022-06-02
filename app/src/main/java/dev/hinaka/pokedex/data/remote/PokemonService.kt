package dev.hinaka.pokedex.data.remote

import dev.hinaka.pokedex.data.responses.pokemon.GetPokemonsResponse
import retrofit2.http.GET

interface PokemonService {
  @GET("pokemon")
  suspend fun getPokemons(): GetPokemonsResponse
}