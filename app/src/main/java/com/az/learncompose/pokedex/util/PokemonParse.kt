package com.az.learncompose.pokedex.util

import androidx.compose.ui.graphics.Color
import com.az.learncompose.pokedex.data.remote.responses.Stat
import com.az.learncompose.pokedex.data.remote.responses.Type
import com.az.learncompose.pokedex.ui.theme.*

fun parseTypeToColor(type: Type): Color {
    return when(type.type.name.lowercase()){
        "normal" -> TypeNormal
        "fire" -> TypeFire
        "water" -> TypeWater
        "electric" -> TypeElectric
        "grass" -> TypeGrass
        "ice" -> TypeIce
        "fighting" -> TypeFighting
        "poison" -> TypePoison
        "ground" -> TypeGround
        "flying" -> TypeFlying
        "psychic" -> TypePsychic
        "bug" -> TypeBug
        "rock" -> TypeRock
        "ghost" -> TypeGhost
        "dragon" -> TypeDragon
        "dark" -> TypeDark
        "steel" -> TypeSteel
        "fairy" -> TypeFairy
        else -> Color.Black
    }
}

fun parseStatToColor(stat: Stat): Color{
    return when(stat.stat.name.lowercase()){
        "hp" -> HPColor
        "attack" -> AtkColor
        "defense" -> DefColor
        "special-attack" -> SpAtkColor
        "special-defence" -> SpDefColor
        "speed" -> SpdColor
        else -> Color.White
    }
}

fun parseStatToAbbr(stat: Stat): String{
    return when(stat.stat.name.lowercase()){
        "hp" -> "HP"
        "attack" -> "Atk"
        "defense" -> "Def"
        "special-attack" -> "SpAtk"
        "special-defence" -> "SpDef"
        "speed" -> "Spd"
        else -> ""
    }
}