package com.example.dogapp.ui.screen.main

import android.content.Intent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.grid.GridItemSpan
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CircularProgressIndicator
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
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.dogapp.R
import com.example.dogapp.data.source.Resource
import com.example.dogapp.domain.model.PetsItem
import com.example.dogapp.ui.component.Banner
import com.example.dogapp.ui.component.Header
import com.example.dogapp.ui.component.PetItem
import com.example.dogapp.ui.component.RadioCategory
import com.example.dogapp.ui.screen.detail.DetailActivity
import com.example.dogapp.ui.theme.DogAppTheme
import com.example.dogapp.ui.theme.plusJakarta
import org.koin.androidx.compose.koinViewModel

@Composable
fun MainScreen(
    mainViewModel: MainViewModel = koinViewModel()
) {
    val petListState by mainViewModel.list.observeAsState()
    Scaffold(
        modifier = Modifier
            .background(color = colorResource(R.color.white))
    ) {
        MainScreenContent(
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
fun MainScreenContent(
    modifier: Modifier = Modifier,
    pets: Resource<List<PetsItem>>?,
    onRefresh: () -> Unit,
    mainViewModel: MainViewModel
) {
    val context = LocalContext.current
    Column(
        modifier = modifier.fillMaxSize()
    ) {
        Header()
        Banner()
        RadioCategory()
        Row(
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically,
            modifier = modifier
                .padding(horizontal = 20.dp)
                .fillMaxWidth()
        ) {
            Text(
                text = "Popular Pets",
                fontFamily = plusJakarta,
                fontWeight = FontWeight.ExtraBold,
                fontSize = 23.sp,
            )
            Text(
                text = "See All",
                fontFamily = plusJakarta,
                color = colorResource(R.color.primary_color),
                fontWeight = FontWeight.Medium,
                fontSize = 15.sp,
                modifier = modifier.clickable { context.startActivity(Intent(context, DetailActivity::class.java)) }
            )
        }
        when(pets) {
            is Resource.Loading -> {
                CircularProgressIndicator(modifier = Modifier
                    .fillMaxWidth()
                    .wrapContentSize())
            }
            is Resource.Success -> {
                LazyRow(
                    modifier = Modifier.fillMaxWidth()
                ) {
                    items(pets.data.take(4)) { pet ->
                        PetItem(pet = pet, mainViewModel = mainViewModel)
                    }
                }
            }
            is Resource.Error -> {
                Text(
                    text = "Error: ${pets.message}",
                    color = Color.Red,
                    modifier = Modifier.fillMaxWidth().wrapContentSize()
                )
            }

            else -> {}
        }

    }
}

@Preview(showBackground = true)
@Composable
fun MainScreenPreview() {
    DogAppTheme {
        MainScreen()
    }
}