package com.example.dogapp.ui.screen.detail

import android.app.Activity
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.GridItemSpan
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.CircularProgressIndicator
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
import com.example.dogapp.domain.model.PetsItem
import com.example.dogapp.ui.component.Banner
import com.example.dogapp.ui.component.Header
import com.example.dogapp.ui.component.PetItem
import com.example.dogapp.ui.component.RadioCategory
import com.example.dogapp.ui.screen.main.MainScreenContent
import com.example.dogapp.ui.screen.main.MainViewModel
import com.example.dogapp.ui.theme.plusJakarta
import org.koin.androidx.compose.koinViewModel

@Composable
fun DetailScreen(
    mainViewModel: MainViewModel = koinViewModel()
) {
    val petListState by mainViewModel.list.observeAsState()
    Scaffold(
        modifier = Modifier
            .background(color = colorResource(R.color.white))
    ) {
        DetailScreenContent(
            modifier = Modifier.padding(it),
            pets = petListState,
            onRefresh = { mainViewModel.getListPet() },
            mainViewModel = mainViewModel
        )
    }
    LaunchedEffect(Unit) {
        mainViewModel.getListPet()
    }
}

@Composable
fun DetailScreenContent(
    modifier: Modifier = Modifier,
    pets: Resource<List<PetsItem>>?,
    onRefresh: () -> Unit,
    mainViewModel: MainViewModel
) {
    val activity = (LocalContext.current as? Activity)
    LazyVerticalGrid(
        columns = GridCells.Fixed(2),
        modifier = modifier.fillMaxHeight(),
        horizontalArrangement = Arrangement.spacedBy(4.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        item(span = { GridItemSpan(2) }) {
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
                    modifier = Modifier.padding(start = 25.dp),
                    text = "List Pets",
                    fontFamily = plusJakarta,
                    fontWeight = FontWeight.ExtraBold,
                    color = Color.Black,
                    fontSize = 24.sp
                )
            }
        }
        when (pets) {
            is Resource.Loading -> {
                item(span = { GridItemSpan(2) }) {
                    CircularProgressIndicator(modifier = Modifier
                        .fillMaxWidth()
                        .wrapContentSize())
                }
            }

            is Resource.Success -> {
                items(pets.data) { pets ->
                    PetItem(pet = pets, mainViewModel = mainViewModel)
                }
            }

            is Resource.Error -> {
                item(span = { GridItemSpan(2) }) {
                    Text(
                        text = "Error: ${pets.message}",
                        color = Color.Red,
                        modifier = Modifier
                            .fillMaxWidth()
                            .wrapContentSize()
                    )
                }
            }

            else -> {}
        }
    }
}