package com.example.jetpackcompose.screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.jetpackcompose.R
import com.example.jetpackcompose.common.SpacerHeight
import com.example.jetpackcompose.common.SpacerWidth
import com.example.jetpackcompose.data.pizzaList
import com.example.jetpackcompose.ui.theme.RedColor
import com.example.jetpackcompose.utilities.NavigationRoute
import com.example.jetpackcompose.utilities.SharedViewModel
import kotlinx.coroutines.delay

@Composable
fun PizzaDetailsScreen(sharedViewModel: SharedViewModel, navController: NavHostController) {
    val scrollState = rememberScrollState()
    val context = LocalContext.current
    var item by remember { mutableIntStateOf(1) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(start = 16.dp, end = 16.dp, bottom = 16.dp),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.Start
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .verticalScroll(scrollState)
                .weight(1f)
        )
        {
            SpacerHeight(14.dp)
            ImageSlideWithIndicator(imageList = sharedViewModel.pizza!!.otherIcons)
            SpacerHeight(18.dp)
            Text(
                text = sharedViewModel.pizza!!.name,
                style = TextStyle(
                    fontSize = 18.sp,
                    fontWeight = FontWeight.W600,
                    color = Color.Black
                )
            )
            SpacerHeight(10.dp)
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    text = sharedViewModel.pizza!!.price,
                    style = TextStyle(
                        fontSize = 22.sp,
                        fontWeight = FontWeight.W600,
                        color = RedColor
                    )
                )
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Image(
                        painter = painterResource(id = R.drawable.minus),
                        contentDescription = null,
                        modifier = Modifier.size(22.dp).clickable { if (item>1) item-- }
                    )
                    Text(
                        text = item.toString(),
                        style = TextStyle(
                            fontSize = 20.sp,
                            fontWeight = FontWeight.W600,
                            color = Color.Black,
                            textAlign = TextAlign.Center
                        ),
                        modifier = Modifier.width(40.dp)
                    )
                    Image(
                        painter = painterResource(id = R.drawable.add),
                        contentDescription = null,
                        modifier = Modifier.size(22.dp).clickable { item++ }
                    )
                }
            }
            SpacerHeight(18.dp)
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    text = "Description",
                    style = TextStyle(
                        fontSize = 18.sp,
                        fontWeight = FontWeight.W600,
                        color = Color.Black
                    )
                )
                Icon(
                    imageVector = Icons.Default.Favorite,
                    contentDescription = null,
                    modifier = Modifier.size(26.dp)
                )
            }
            SpacerHeight(10.dp)
            Text(
                text = sharedViewModel.pizza!!.description,
                style = TextStyle(
                    fontSize = 15.sp,
                    fontWeight = FontWeight.W400,
                    color = Color.Gray
                )
            )
            SpacerHeight(18.dp)
            Text(
                text = "Similar Products",
                style = TextStyle(
                    fontSize = 18.sp,
                    fontWeight = FontWeight.W600,
                    color = Color.Black
                )
            )
            SpacerHeight(12.dp)
            LazyRow {
                items(pizzaList) { item ->
                    PizzaSingleItem(pizza = item, onClick = {
                        sharedViewModel.addPizza(item)
                        navController.navigate(NavigationRoute.PIZZA_DETAILS_SCREEN)
                    })
                }
            }
        }
        Button(
            onClick = {

            },
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 1.dp, start = 50.dp, end = 50.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = RedColor, // Background color
                contentColor = Color.White   // Text color
            ),
        ) {
            Text(text = "Add to cart", style = TextStyle(fontSize = 16.sp, color = Color.White))
        }
    }
}

@Composable
fun ImageSliderItem(image: Int) {
    Image(
        painter = painterResource(id = image),
        contentDescription = null,
        modifier = Modifier
            .fillMaxWidth()
            .height(200.dp)
    )
}

@Composable
fun Indicator(active: Boolean) {
    val color = if (active) Color.Red else Color.Gray
    Box(
        modifier = Modifier
            .clip(shape = CircleShape)
            .size(10.dp)
            .background(color)
    )
}

@Composable
fun ImageSlideWithIndicator(imageList: List<Int>) {
    var currentIndex by remember { mutableIntStateOf(0) }
    LaunchedEffect(Unit) {
        while (true) {
            delay(5000)
            val nextIndex = (currentIndex + 1) % imageList.size
            currentIndex = nextIndex
        }
    }

    Card(
        modifier = Modifier
            .fillMaxWidth(),
        shape = RoundedCornerShape(8.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White)
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(10.dp), horizontalAlignment = Alignment.CenterHorizontally
        ) {
            ImageSliderItem(image = imageList[currentIndex])
            SpacerHeight()
            Row {
                imageList.forEachIndexed { index, _ ->
                    Indicator(active = index == currentIndex)
                    if (index < imageList.size - 1) {
                        Spacer(modifier = Modifier.width(5.dp))
                    }
                }
            }
            SpacerHeight()
        }
    }
}