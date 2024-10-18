package com.example.dogapp.ui.screen.favorite

import android.app.Activity
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.dogapp.R
import com.example.dogapp.data.source.Resource
import com.example.dogapp.data.source.local.entity.PetsEntity
import com.example.dogapp.ui.component.CardFavorite
import com.example.dogapp.ui.screen.main.MainViewModel
import com.example.dogapp.ui.theme.plusJakarta
import org.koin.androidx.compose.koinViewModel

@Composable
fun FavoriteScreen(
    mainViewModel: MainViewModel = koinViewModel(),
) {
    val favoritePets by mainViewModel.favorite.observeAsState(Resource.Loading(true))

    LaunchedEffect(Unit) {
        mainViewModel.getFavoritePets()
    }

    Scaffold(
        modifier = Modifier.background(color = Color.White)
    ) {
        FavoriteScreenContent(
            modifier = Modifier.padding(it),
            favoritePets = favoritePets,
            mainViewModel = mainViewModel,
        )
    }
}

@Composable
fun FavoriteScreenContent(
    modifier: Modifier = Modifier,
    favoritePets: Resource<List<PetsEntity>>?,
    mainViewModel: MainViewModel
) {
    val activity = (LocalContext.current as? Activity)
    Column(
        modifier = modifier.fillMaxSize()
    ) {
        Row(
            modifier = modifier
                .fillMaxWidth()
                .padding(top = 20.dp, start = 15.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            IconButton(
                onClick = { activity?.onBackPressed() },
                modifier = modifier.padding(top = 5.dp)
            ) {
                Icon(Icons.Filled.ArrowBack, contentDescription = null)
            }
            Text(
                text = "Favorite Pets",
                fontFamily = plusJakarta,
                fontWeight = FontWeight.Bold,
                color = colorResource(R.color.black),
                letterSpacing = (-3 * 20 / 100).sp,
                fontSize = 20.sp,
                modifier = modifier.padding(start = 15.dp)
            )
        }
        Divider(color = Color.Gray, thickness = 1.dp, modifier = modifier.padding(top = 10.dp))

        when (favoritePets) {
            is Resource.Loading -> {
                CircularProgressIndicator(modifier = modifier.padding(16.dp))
            }

            is Resource.Success -> {
                LazyColumn(
                    modifier = modifier.fillMaxHeight()
                ) {
                    items(favoritePets.data) { pets ->
                        CardFavorite(
                            pets = pets,
                            removeFavorite = { mainViewModel.removeFavorite(pets, false) }
                        )
                    }
                }
            }

            is Resource.Error -> {
                Text(
                    text = "Error: ${favoritePets.message}",
                    color = Color.Red,
                    modifier = modifier.padding(16.dp)
                )
            }

            else -> {}
        }
    }
}