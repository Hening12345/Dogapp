package com.example.dogapp.ui.component

import android.content.Intent
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.dogapp.R
import com.example.dogapp.ui.screen.favorite.FavoriteActivity
import com.example.dogapp.ui.theme.DogAppTheme
import com.example.dogapp.ui.theme.plusJakarta

@Composable
fun Header(
    modifier: Modifier = Modifier
) {
    val context = LocalContext.current
    Row(
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically,
        modifier = modifier
            .padding(start = 30.dp, end = 50.dp, top = 50.dp, bottom = 30.dp)
            .fillMaxWidth()
    ) {
        Column {
            Text(
                text = "Hello!",
                fontFamily = plusJakarta,
                fontWeight = FontWeight.Medium,
                fontSize = 16.sp
            )
            Text(
                text = "Hening Rifqi",
                fontFamily = plusJakarta,
                fontWeight = FontWeight.ExtraBold,
                fontSize = 25.sp,
                letterSpacing = (-3 * 20 / 100).sp,
            )

        }
        Image(
            painter = painterResource(R.drawable.paw),
            contentDescription = null,
            modifier = modifier.size(40.dp)
                .clickable { context.startActivity(Intent(context, FavoriteActivity::class.java)) }
        )
    }
}

@Preview(showBackground = true)
@Composable
fun HeaderPreview() {
    DogAppTheme {
        Header()
    }
}