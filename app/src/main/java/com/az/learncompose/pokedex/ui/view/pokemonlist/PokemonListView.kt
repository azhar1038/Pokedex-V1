package com.az.learncompose.pokedex.ui.view.pokemonlist

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Warning
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.Center
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.scale
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import coil.request.ImageRequest
import com.az.learncompose.pokedex.R
import com.az.learncompose.pokedex.data.models.PokedexListEntry
import com.az.learncompose.pokedex.ui.theme.RobotoCondensed
import com.google.accompanist.coil.rememberCoilPainter
import com.google.accompanist.imageloading.ImageLoadState

@Composable
fun PokemonListView(
    navController: NavController
) {
    Surface(
        color = MaterialTheme.colors.background,
        modifier = Modifier.fillMaxSize()
    ) {
        Column {
            Spacer(Modifier.height(20.dp))
            Image(
                painter = painterResource(R.drawable.ic_international_pokemon_logo),
                contentDescription = "Pokemon Logo",
                modifier = Modifier
                    .fillMaxWidth()
                    .align(CenterHorizontally)
            )
            SearchBar(
                hint = "Search...",
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp)
            ) {

            }
            PokedexRow(
                rowIndex = 0,
                entries = listOf(
                    PokedexListEntry(
                        "bulbasaur",
                        "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/1.png",
                        1
                    ),
                    PokedexListEntry(
                        "ivysaur",
                        "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/2.png",
                        2
                    )
                ),
                navController = navController
            )
        }
    }
}

@Composable
fun SearchBar(
    modifier: Modifier = Modifier,
    hint: String = "",
    onSearch: (String) -> Unit = {},
) {
    var text by remember {
        mutableStateOf("")
    }

    var isHintDisplayed by remember {
        mutableStateOf(text == "")
    }

    Box(modifier = modifier) {
        BasicTextField(
            value = text,
            onValueChange = {
                text = it
                isHintDisplayed = it == ""
                onSearch(it)
            },
            maxLines = 1,
            singleLine = true,
            textStyle = TextStyle(color = Color.Black),
            modifier = Modifier
                .fillMaxWidth()
                .shadow(5.dp, shape = CircleShape)
                .background(Color.White, shape = CircleShape)
                .padding(horizontal = 20.dp, vertical = 16.dp)
//                .onFocusChanged {
//                    isHintDisplayed = it.isFocused
//                }
        )
        if (isHintDisplayed) {
            Text(
                hint,
                color = Color.LightGray,
                modifier = Modifier
                    .padding(horizontal = 20.dp, vertical = 15.dp)
            )
        }
    }
}

@Composable
fun PokedexEntry(
    entry: PokedexListEntry,
    navController: NavController,
    modifier: Modifier = Modifier,
    viewModel: PokemonListViewModel = hiltViewModel()
) {
    val defaultDominantColor = MaterialTheme.colors.surface
    var dominantColor by remember {
        mutableStateOf(defaultDominantColor)
    }

    val painter = rememberCoilPainter(
        request = ImageRequest.Builder(LocalContext.current)
            .data(entry.imageUrl)
            .target {
                viewModel.calcDominantColor(it) { color ->
                    dominantColor = color
                }
            }
            .build(),
        fadeIn = true
    )



    Box(
        contentAlignment = Center,
        modifier = modifier
            .shadow(5.dp, shape = RoundedCornerShape(10.dp))
            .clip(RoundedCornerShape(10.dp))
            .aspectRatio(1f)
            .background(
                Brush.verticalGradient(
                    listOf(
                        dominantColor,
                        defaultDominantColor,
                    )
                )
            )
            .clickable {
                navController.navigate(
                    "pokemon_detail/${dominantColor.toArgb()}/${entry.pokemonName}"
                )
            }
    ) {
        Column {
            Box(
                modifier = Modifier
                    .size(120.dp)
                    .align(CenterHorizontally)
            ) {
                Image(
                    painter = painter,
                    contentDescription = "${entry.pokemonName} Image",
                )
                when (painter.loadState) {
                    is ImageLoadState.Loading -> {
                        CircularProgressIndicator(
                            color = MaterialTheme.colors.primary,
                            modifier = Modifier.scale(0.5f)
                        )
                    }
                    is ImageLoadState.Error -> {
                        Icon(
                            Icons.Rounded.Warning,
                            contentDescription = "Failed to load image",
                        )
                    }
                }
            }
            Text(
                text = entry.pokemonName,
                fontFamily = RobotoCondensed,
                fontSize = 20.sp,
                textAlign = TextAlign.Center,
                modifier = Modifier.fillMaxWidth()
            )
        }
    }
}

@Composable
fun PokedexRow(
    rowIndex: Int,
    entries: List<PokedexListEntry>,
    navController: NavController,
) {
    Column {
        Row {
            PokedexEntry(
                entry = entries[rowIndex * 2],
                navController = navController,
                modifier = Modifier.weight(1f)
            )
            Spacer(Modifier.width(16.dp))
            if (entries.size >= rowIndex * 2 + 2) {
                PokedexEntry(
                    entry = entries[rowIndex * 2 + 1],
                    navController = navController,
                    modifier = Modifier.weight(1f)
                )
            } else {
                Spacer(Modifier.weight(1f))
            }
        }
        Spacer(Modifier.height(16.dp))
    }
}

@Preview(showBackground = false)
@Composable
fun PreviewSearchBar() {
    SearchBar(
        hint = "Search"
    )
}