package com.example.dogapp.ui.component

import android.provider.ContactsContract.Data
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.Alignment
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.colorResource
import coil.compose.rememberAsyncImagePainter
import com.example.dogapp.R
import com.example.dogapp.data.source.local.entity.PetsEntity
import com.example.dogapp.domain.model.PetsItem
import com.example.dogapp.ui.screen.main.MainViewModel
import com.example.dogapp.ui.theme.DogAppTheme
import com.example.dogapp.ui.theme.plusJakarta
import com.example.dogapp.utils.DataMapper

@Composable
fun PetItem(pet: PetsItem, mainViewModel: MainViewModel) {
    var isFavorite by remember { mutableStateOf(pet.favorite == true) }
    Card(
        shape = RoundedCornerShape(16.dp),
        elevation = CardDefaults.cardElevation(4.dp),
        colors = CardDefaults.cardColors(
            containerColor = Color.White
        ),
        modifier = Modifier
            .padding(start = 20.dp, top = 20.dp)
            .width(200.dp)
    ) {
        Column(
            modifier = Modifier.padding(8.dp),
        ) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(150.dp)
                    .clip(RoundedCornerShape(16.dp)),
                contentAlignment = Alignment.TopEnd
            ) {
                Image(
                    painter = rememberAsyncImagePainter("https://cdn2.thedogapi.com/images/${pet.referenceImageId}.jpg"),
                    contentDescription = null,
                    contentScale = ContentScale.Crop,
                    modifier = Modifier.fillMaxSize()
                )
                Box(
                    modifier = Modifier
                        .padding(end = 8.dp, top = 8.dp)
                        .size(35.dp)
                        .clip(RoundedCornerShape(10.dp))
                        .background(Color.White)
                        .border(1.dp, Color.Gray, RoundedCornerShape(10.dp))
                        .clickable {
                            isFavorite = !isFavorite!!
                            val petsEntity = DataMapper.mapToPetsEntity(pet, isFavorite!!)
                            mainViewModel.addToFavorite(petsEntity, isFavorite!!)
                        },
                    contentAlignment = Alignment.Center
                ) {
                    Image(
                        painter = painterResource(if (isFavorite!!) R.drawable.heart_selected else R.drawable.heart_not_selected),
                        contentDescription = null,
                        modifier = Modifier.size(20.dp)
                    )
                }
            }
        }
        Text(
            text = pet.name!!,
            fontFamily = plusJakarta,
            fontWeight = FontWeight.ExtraBold,
            fontSize = 18.sp,
            maxLines = 1,
            letterSpacing = (-3 * 20 / 100).sp,
            color = colorResource(R.color.black),
            modifier = Modifier.padding(start = 10.dp, top = 5.dp)
        )
        Text(
            text = pet.bredFor!!,
            color = Color.Gray,
            letterSpacing = (-3 * 20 / 100).sp,
            fontFamily = plusJakarta,
            maxLines = 1,
            fontWeight = FontWeight.Medium,
            modifier = Modifier.padding(start = 10.dp, bottom = 10.dp),
            fontSize = 12.sp
        )
    }
}
