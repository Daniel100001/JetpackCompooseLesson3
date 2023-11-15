package com.example.jetpackcompooselesson3.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Color.Companion.White
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.example.jetpackcompooselesson3.R
import com.example.jetpackcompooselesson3.SuggestionsBox
import com.example.jetpackcompooselesson3.models.PlacesModel
import com.example.jetpackcompooselesson3.ui.theme.ColorForContainer
import com.example.jetpackcompooselesson3.ui.theme.TextColor
import com.example.jetpackcompooselesson3.ui.theme.raiting
import com.example.jetpackcompooselesson3.ui.theme.starColor
import com.example.jetpackcompooselesson3.ui.theme.transparentBlack

@Composable
fun PlacesList(modifier: Modifier) {
    var countries = remember {
        mutableStateListOf<PlacesModel?>(null)
    }

    items(countries)
    val listState = rememberLazyListState()
    LazyColumn(modifier = modifier) {
        item {
            SuggestionsBox(
                modifier = Modifier
                    .padding(top = 24.dp, start = 16.dp, end = 16.dp)
                    .fillMaxWidth()
                    .wrapContentHeight()
            )
        }
        item {
            LazyRow(
                modifier = Modifier
                    .padding(top = 24.dp, start = 12.dp)
                    .fillMaxWidth()
                    .wrapContentHeight(),
                horizontalArrangement = Arrangement.spacedBy(12.dp, Alignment.CenterHorizontally),
                state = listState
            ) {
                items(countries) { country ->
                    country?.let {
                        PlaceItem(modifier = Modifier.wrapContentSize(), country = country)
                    }
                }
            }
        }
        item {
            Text(
                modifier = Modifier.padding(start = 16.dp, top = 40.dp),
                text = "The best tours",
                color = White,
                fontSize = 18.sp
            )
        }

        items(countries) { country ->
            country?.let {
                PlaceColumItem(modifier = Modifier.padding(start = 16.dp,top = 16.dp).wrapContentSize(), country = country)
            }
        }
    }
}

@Composable
fun PlaceItem(modifier: Modifier = Modifier, country: PlacesModel) {
    Box(
        modifier
            .clip(shape = RoundedCornerShape(15.dp))
            .wrapContentSize()
    ) {
        AsyncImage(
            modifier = Modifier.size(height = 160.dp, width = 140.dp),
            model = ImageRequest.Builder(LocalContext.current).data(country.placeImage)
                .crossfade(true).build(),
            contentDescription = "image",
            placeholder = painterResource(
                id = R.drawable.pon,
            ),
            contentScale = ContentScale.Crop
        )
        Row(
            modifier = Modifier
                .padding(top = 8.dp, end = 8.dp)
                .height(18.dp)
                .width(30.dp)
                .align(Alignment.TopEnd)
                .clip(shape = RoundedCornerShape(5.dp))
                .background(Color(transparentBlack.value)),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                modifier = Modifier
                    .padding(start = 4.dp),
                text = country.grade.toString(),
                fontSize = 10.sp,
                color = White
            )
            Icon(
                modifier = Modifier
                    .padding(start = 2.dp, end = 4.dp)
                    .wrapContentSize(),
                painter = painterResource(id = R.drawable.star_ic),
                contentDescription = "star image",
                tint = starColor
            )
        }
        Column(
            modifier = Modifier
                .padding(start = 12.dp, bottom = 8.dp)
                .wrapContentSize()
                .align(Alignment.BottomStart),

            ) {
            Text(text = country.name, color = White, fontSize = 14.sp)

            Row(verticalAlignment = Alignment.CenterVertically) {
                Icon(
                    modifier = Modifier,
                    painter = painterResource(id = R.drawable.location_ic),
                    contentDescription = "location image",
                    tint = White
                )
                Text(
                    text = country.placeLocation,
                    color = White,
                    fontSize = 10.sp
                )
            }
        }
    }
}

@Preview
@Composable
fun PreviewPlaceItem() {
    PlaceItem(
        modifier = Modifier, country = PlacesModel(
            "Nusa Pedina",
            4.5,
            "Bali, Indonesia",
            "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcT9R1iS8baHHD73py0Fzh5Suz-L3QEk4RQI6Q&usqp=CAU",
            "12 - 18 Jan 2021",
            450,
            "Egypt"
        )
    )
}

@Composable
fun PlaceColumItem(modifier: Modifier, country: PlacesModel) {
    Row(modifier = modifier, verticalAlignment = Alignment.CenterVertically) {
        AsyncImage(
            modifier = Modifier
                .padding(12.dp)
                .width(90.dp)
                .height(90.dp)
                .clip(
                    shape = RoundedCornerShape
                        (6.dp)
                ),
            model = ImageRequest.Builder(LocalContext.current).data(country.placeImage)
                .crossfade(true).build(),
            contentDescription = "image",
            placeholder = painterResource(
                id = R.drawable.pon,
            ),
            contentScale = ContentScale.Crop
        )
        Column(modifier = Modifier) {
            Box(
                modifier = Modifier
                    .wrapContentSize()
                    .fillMaxWidth()
            ) {

                Text(
                    modifier = Modifier.align(Alignment.CenterStart),
                    text = "Western Desert",
                    fontSize = 18.sp,
                    color = White
                )

                Row(
                    modifier = Modifier
                        .padding(end = 12.dp)
                        .height(22.dp)
                        .width(44.dp)
                        .clip(shape = RoundedCornerShape(5.dp))
                        .background(color = raiting)
                        .align(alignment = Alignment.CenterEnd),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Center
                ) {
                    Text(
                        modifier = Modifier
                            .padding(start = 4.dp),
                        text = country.grade.toString(),
                        fontSize = 10.sp,
                        color = White
                    )
                    Icon(
                        modifier = Modifier
                            .padding(start = 2.dp, end = 4.dp)
                            .wrapContentSize(),
                        painter = painterResource(id = R.drawable.star_ic),
                        contentDescription = "star image",
                        tint = starColor
                    )
                }
            }

            Row(
                modifier = Modifier.padding(vertical = 12.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.ic_calendar),
                    contentDescription = "Icon calendar",
                    tint = TextColor
                )
                Text(modifier = Modifier.padding(start = 4.dp), text = country.data, color = White)
            }

            Row(modifier = Modifier, verticalAlignment = Alignment.CenterVertically) {
                Icon(
                    painter = painterResource(id = R.drawable.ic_dollar_square),
                    contentDescription = "Icon dollar",
                    tint = TextColor
                )
                Text(
                    modifier = Modifier.padding(start = 4.dp),
                    text = country.money.toString(),
                    color = White
                )
                Text(text = " / Day", color = TextColor)
                Icon(
                    modifier = Modifier
                        .padding(start = 26.dp)
                        .wrapContentSize(),
                    painter = painterResource(id = R.drawable.location_ic),
                    contentDescription = "location Icon",
                    tint = TextColor
                )
                Text(
                    modifier = Modifier.padding(start = 4.dp),
                    text = country.location,
                    color = White
                )

            }
        }
    }
}


@Preview
@Composable
fun PreviewPlaceColumItem() {
    PlaceColumItem(
        modifier = Modifier
            .padding(top = 16.dp, start = 16.dp, end = 16.dp)
            .fillMaxWidth()
            .wrapContentHeight()
            .background(color = ColorForContainer, shape = RoundedCornerShape(10.dp)),
        country = PlacesModel(
            "Nusa Pedina",
            4.5,
            "Bali, Indonesia",
            "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcT9R1iS8baHHD73py0Fzh5Suz-L3QEk4RQI6Q&usqp=CAU",
            "12 - 18 Jan 2021",
            450,
            "Egypt"
        )
    )
}

fun items(countries: SnapshotStateList<PlacesModel?>) = with(countries) {
    add(
        PlacesModel(
            "Nusa Pedina",
            4.5,
            "Bali, Indonesia",
            "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcT9R1iS8baHHD73py0Fzh5Suz-L3QEk4RQI6Q&usqp=CAU",
            "12 - 18 Jan 2021",
            450,
            "Egypt"
        )
    )
    add(
        PlacesModel(
            "Nusa Pedina",
            4.5,
            "Bali, Indonesia",
            "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcT9R1iS8baHHD73py0Fzh5Suz-L3QEk4RQI6Q&usqp=CAU",
            "12 - 18 Jan 2021",
            450,
            "Egypt"
        )
    )
    add(
        PlacesModel(
            "Nusa Pedina",
            4.5,
            "Bali, Indonesia",
            "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcT9R1iS8baHHD73py0Fzh5Suz-L3QEk4RQI6Q&usqp=CAU",
            "12 - 18 Jan 2021",
            450,
            "Egypt"
        )
    )
    add(
        PlacesModel(
            "Nusa Pedina",
            4.5,
            "Bali, Indonesia",
            "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcT9R1iS8baHHD73py0Fzh5Suz-L3QEk4RQI6Q&usqp=CAU",
            "12 - 18 Jan 2021",
            450,
            "Egypt"
        )
    )
    add(
        PlacesModel(
            "Nusa Pedina",
            4.5,
            "Bali, Indonesia",
            "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcT9R1iS8baHHD73py0Fzh5Suz-L3QEk4RQI6Q&usqp=CAU",
            "12 - 18 Jan 2021",
            450,
            "Egypt"
        )
    )
    add(
        PlacesModel(
            "Nusa Pedina",
            4.5,
            "Bali, Indonesia",
            "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcT9R1iS8baHHD73py0Fzh5Suz-L3QEk4RQI6Q&usqp=CAU",
            "12 - 18 Jan 2021",
            450,
            "Egypt"
        )
    )
    add(
        PlacesModel(
            "Nusa Pedina",
            4.5,
            "Bali, Indonesia",
            "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcT9R1iS8baHHD73py0Fzh5Suz-L3QEk4RQI6Q&usqp=CAU",
            "12 - 18 Jan 2021",
            450,
            "Egypt"
        )
    )
    add(
        PlacesModel(
            "Nusa Pedina",
            4.5,
            "Bali, Indonesia",
            "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcT9R1iS8baHHD73py0Fzh5Suz-L3QEk4RQI6Q&usqp=CAU",
            "12 - 18 Jan 2021",
            450,
            "Egypt"
        )
    )
    add(
        PlacesModel(
            "Nusa Pedina",
            4.5,
            "Bali, Indonesia",
            "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcT9R1iS8baHHD73py0Fzh5Suz-L3QEk4RQI6Q&usqp=CAU",
            "12 - 18 Jan 2021",
            450,
            "Egypt"
        )
    )
    add(
        PlacesModel(
            "Nusa Pedina",
            4.5,
            "Bali, Indonesia",
            "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcT9R1iS8baHHD73py0Fzh5Suz-L3QEk4RQI6Q&usqp=CAU",
            "12 - 18 Jan 2021",
            450,
            "Egypt"
        )
    )

}


