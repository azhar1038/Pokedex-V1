package com.az.learncompose.pokedex.data.remote.responses

data class Pokemon(
    val abilities: List<PokemonAbility>,
    val baseExperience: Int,
    val forms: List<PokemonForm>,
    val gameIndices: List<PokemonGameIndice>,
    val height: Int,
    val heldItems: List<PokemonHeldItem>,
    val id: Int,
    val isDefault: Boolean,
    val locationAreaEncounters: String,
    val moves: List<PokemonMove>,
    val name: String,
    val order: Int,
    val pastTypes: List<Any>,
    val species: PokemonSpecies,
    val sprites: PokemonSprites,
    val stats: List<PokemonStat>,
    val types: List<PokemonType>,
    val weight: Int
)