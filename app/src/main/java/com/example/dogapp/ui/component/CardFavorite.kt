package com.example.dogapp.ui.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Delete
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.rememberAsyncImagePainter
import com.example.dogapp.R
import com.example.dogapp.data.source.local.entity.PetsEntity
import com.example.dogapp.ui.theme.DogAppTheme
import com.example.dogapp.ui.theme.plusJakarta

@Composable
fun CardFavorite(
    modifier: Modifier = Modifier,
    pets: PetsEntity,
    removeFavorite: (PetsEntity) -> Unit,
) {
    var isFavorite by remember { mutableStateOf(pets.isFavorite == true) }

    Card(
        modifier = modifier
            .fillMaxWidth()
            .padding(vertical = 25.dp),
        colors = CardDefaults.cardColors(
            containerColor = Color.Transparent
        )
    ) {
        Row(
            modifier = modifier
                .padding(start = 15.dp)
                .height(IntrinsicSize.Min),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Image(
                painter = rememberAsyncImagePainter("https://cdn2.thedogapi.com/images/${pets.image}.jpg"),
                contentDescription = null,
                contentScale = ContentScale.FillBounds,
                modifier = modifier
                    .size(100.dp)
                    .clip(RoundedCornerShape(15.dp))
            )
            Spacer(modifier = modifier.width(20.dp))
            Column(
                modifier = modifier
                    .padding(vertical = 10.dp)
                    .weight(1f)
            ) {
                Text(
                    text = pets.name,
                    color = colorResource(R.color.black),
                    fontFamily = plusJakarta,
                    fontWeight = FontWeight.ExtraBold,
                    fontSize = 18.sp,
                    letterSpacing = (-3 * 20 / 100).sp,
                )
                Text(
                    text = pets.bredFor,
                    color = Color.Gray,
                    fontFamily = plusJakarta,
                    letterSpacing = (-3 * 20 / 100).sp,
                    fontWeight = FontWeight.Medium,
                    fontSize = 14.sp,
                    modifier = modifier.padding(top = 10.dp)
                )
            }
            Image(
                painter = painterResource(if (isFavorite) R.drawable.heart_selected else R.drawable.heart_not_selected),
                contentDescription = null,
                modifier = modifier.size(40.dp)
                    .align(Alignment.Top)
                    .padding(top = 10.dp, end = 20.dp)
                    .clickable {
                        isFavorite = !isFavorite
                        removeFavorite(pets.copy(isFavorite = !isFavorite))
                    }
            )
        }
    }
}

