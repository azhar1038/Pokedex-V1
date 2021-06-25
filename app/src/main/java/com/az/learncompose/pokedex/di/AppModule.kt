package com.az.learncompose.pokedex.di

import com.az.learncompose.pokedex.data.remote.PokeApi
import com.az.learncompose.pokedex.repository.pokemon.DefaultPokemonRepository
import com.az.learncompose.pokedex.repository.pokemon.PokemonRepository
import com.az.learncompose.pokedex.util.Constants.BASE_URL
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Singleton
    @Provides
    fun providePokeApi(): PokeApi{
        val moshi = Moshi.Builder()
            .add(KotlinJsonAdapterFactory())
            .build()
        return Retrofit
            .Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(MoshiConverterFactory.create(moshi))
            .build()
            .create(PokeApi::class.java)
    }

    @Singleton
    @Provides
    fun providePokemonRepository(
        pokeApi: PokeApi
    ): PokemonRepository = DefaultPokemonRepository(pokeApi)
}