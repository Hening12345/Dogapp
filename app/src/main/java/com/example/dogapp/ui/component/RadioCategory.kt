package com.example.dogapp.ui.component

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.RadioButton
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
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.dogapp.R
import com.example.dogapp.domain.model.Category
import com.example.dogapp.ui.theme.DogAppTheme
import com.example.dogapp.ui.theme.plusJakarta

@Composable
fun RadioCategory() {
    val categories = listOf(
        Category("Dog"),
        Category("Cat"),
        Category("Bird"),
        Category("Fish"),
        Category("Rabbit")
    )

    var selectedCategory by remember { mutableStateOf(categories[0]) }
    val scrollState = rememberScrollState()

    Row(
        modifier = Modifier
            .horizontalScroll(scrollState)
            .fillMaxWidth()
            .padding(8.dp)
    ) {
        categories.forEach { category ->
            Box(
                modifier = Modifier
                    .padding(horizontal = 8.dp)
                    .width(90.dp)
                    .clip(RoundedCornerShape(16.dp))
                    .background(
                        if (category == selectedCategory) colorResource(R.color.primary_color) else Color.Transparent
                    )
                    .border(
                        width = 1.dp,
                        color = if (category == selectedCategory) Color.Transparent else Color.Gray,
                        shape = RoundedCornerShape(16.dp)
                    )
                    .clickable { selectedCategory = category }
                    .padding(vertical = 8.dp, horizontal = 16.dp)
            ) {
                Text(
                    text = category.name,
                    fontFamily = plusJakarta,
                    fontWeight = if (category == selectedCategory) FontWeight.Bold else FontWeight.Medium,
                    modifier = Modifier.align(Alignment.Center),
                    color = if (category == selectedCategory) Color.White else Color.Gray,
                    fontSize = 16.sp
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun RadioCategoryPreview() {
    DogAppTheme {
        RadioCategory()
    }
}