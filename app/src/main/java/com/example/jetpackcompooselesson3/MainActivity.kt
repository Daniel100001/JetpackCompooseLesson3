package com.example.jetpackcompooselesson3

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.magnifier
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.jetpackcompooselesson3.ui.PlaceItem
import com.example.jetpackcompooselesson3.ui.PlacesList
import com.example.jetpackcompooselesson3.ui.theme.BackgroundColor
import com.example.jetpackcompooselesson3.ui.theme.GrayForTextField
import com.example.jetpackcompooselesson3.ui.theme.JetpackCompooseLesson3Theme
import com.example.jetpackcompooselesson3.ui.theme.TextColor
import com.example.jetpackcompooselesson3.ui.theme.TextFieldColor

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            JetpackCompooseLesson3Theme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    MainContainer(modifier = Modifier.fillMaxSize())
                }
            }
        }
    }
}

@Composable
fun MainContainer(modifier: Modifier = Modifier) {
    Column(modifier = modifier.background(color = BackgroundColor)) {
        UserInfo(
            modifier = Modifier
                .padding(top = 30.dp, start = 16.dp, end = 16.dp)
                .wrapContentHeight()
                .fillMaxWidth()
        )

        SearchView(
            modifier = Modifier
                .padding(top = 25.dp, start = 16.dp, end = 16.dp)
                .fillMaxWidth()
                .wrapContentHeight()
        )
        PlacesList(modifier = Modifier.fillMaxSize())

    }
}


@Composable
fun UserInfo(modifier: Modifier) {
    Box(modifier = modifier) {
        Row(
            modifier = Modifier
                .wrapContentSize()
        ) {
            Image(
                modifier = Modifier
                    .clip(shape = CircleShape)
                    .width(40.dp)
                    .height(40.dp),
                painter = painterResource(id = R.drawable.profile_image),
                contentDescription = "profile image"
            )
            Column(
                modifier = Modifier
                    .wrapContentSize()
                    .padding(start = 5.dp)
            ) {
                Text(text = "Welcome back", color = TextColor, fontSize = 12.sp)
                Text(text = "Mohammad Mahdi", color = Color.White, fontSize = 16.sp)
            }
        }
        Image(
            modifier = Modifier.align(alignment = Alignment.CenterEnd),
            painter = painterResource(id = R.drawable.notification_ic),
            contentDescription = "notification image"
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SearchView(modifier: Modifier) {
    var text by remember {
        mutableStateOf(TextFieldValue(""))
    }

    TextField(
        modifier = modifier
            .clip(shape = RoundedCornerShape(10.dp)),
        value = text,
        onValueChange = {
            text = it
        },
        colors = TextFieldDefaults.textFieldColors(
            containerColor = TextFieldColor,
            unfocusedIndicatorColor = TextFieldColor
        ),
        leadingIcon = {
            Icon(
                modifier = Modifier.padding(start = 12.dp),
                painter = painterResource(id = R.drawable.search_ic),
                contentDescription = "search icon", tint = GrayForTextField
            )

        }, label = {
            Text(modifier = Modifier.padding(start = 8.dp), text = "Search")

        }, trailingIcon = {
            Icon(
                modifier = Modifier.padding(end = 12.dp),
                painter = painterResource(id = R.drawable.filter_ic),
                contentDescription = "filter icon",
                tint = GrayForTextField
            )
        }
    )
}

@Composable
fun SuggestionsBox(modifier: Modifier) {
    Box(modifier = modifier) {
        Text(text = "Suggestions for you", fontSize = 18.sp, color = Color.White)

        Row(
            modifier = Modifier
                .wrapContentSize()
                .align(alignment = Alignment.CenterEnd)
        ) {
            Text(text = "See all", fontSize = 12.sp, color = Color.White)
            Icon(
                modifier = Modifier
                    .padding(start = 4.dp)
                    .wrapContentSize(),
                painter = painterResource(id = R.drawable.arrow_right),
                contentDescription = "see continue button",
                tint = Color.White
            )
        }
    }

}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    JetpackCompooseLesson3Theme {
        MainContainer(modifier = Modifier.fillMaxSize())
    }
}