package com.example.jetpackcompose.screen

import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.example.jetpackcompose.R
import com.example.jetpackcompose.common.SpacerHeight
import com.example.jetpackcompose.data.Pizza
import com.example.jetpackcompose.data.pizzaList
import com.example.jetpackcompose.room_db.PizzaEntity
import com.example.jetpackcompose.ui.theme.DarkBlackColor
import com.example.jetpackcompose.ui.theme.LightGrayColor
import com.example.jetpackcompose.ui.theme.RedColor
import com.example.jetpackcompose.ui.theme.YellowColor
import com.example.jetpackcompose.utilities.NavigationRoute
import com.example.jetpackcompose.utilities.STATICS
import com.example.jetpackcompose.view_models.PizzaViewModel
import com.example.jetpackcompose.view_models.SharedViewModel

@Composable
fun HomeScreen(
    navController: NavHostController
) {
    val context = LocalContext.current
    val sharedViewModel = hiltViewModel<SharedViewModel>()
    val pizzaViewModel = hiltViewModel<PizzaViewModel>()
    var currentToast by remember { mutableStateOf<Toast?>(null) }

    LazyVerticalGrid(
        columns = GridCells.Fixed(2),
        modifier = Modifier.padding(horizontal = 5.dp),
        contentPadding = PaddingValues(bottom = 60.dp)
    ) {
        items(pizzaList.size) { index ->
            PizzaSingleItem(pizza = pizzaList[index], onClick = {
                sharedViewModel.addPizza(pizzaList[index])
                navController.navigate(NavigationRoute.PIZZA_DETAILS_SCREEN)
            }, addToCart = {
                val pizza = PizzaEntity(
                    it.id,
                    it.image,
                    it.name,
                    it.description,
                    it.price,
                    STATICS.CART,
                    1
                )
                pizzaViewModel.insertPizza(pizza)
                Toast.makeText(context, "This item added in cart", Toast.LENGTH_SHORT).show()
            }, onFavoriteClick = {
                currentToast?.cancel()
                val pizza = PizzaEntity(
                    it.id,
                    it.image,
                    it.name,
                    it.description,
                    it.price,
                    STATICS.FAVORITE,
                    1
                )
                pizzaViewModel.insertPizza(pizza)
                currentToast =
                    Toast.makeText(context, "This item added in favorite", Toast.LENGTH_SHORT)
                currentToast?.show()
            })
        }
    }
}

@Composable
fun PizzaSingleItem(
    pizza: Pizza,
    onClick: () -> Unit,
    addToCart: (Pizza) -> Unit,
    onFavoriteClick: (Pizza) -> Unit
) {
    var addToCartStatus by remember { mutableStateOf(false) }
    var favoriteStatus by remember { mutableStateOf(false) }
    val context = LocalContext.current
    Card(
        modifier = Modifier
            .width(180.dp)
            .padding(5.dp)
            .clickable { onClick() }, shape = RoundedCornerShape(15.dp)
    ) {
        Box {
            Column(
                modifier = Modifier
                    .padding(8.dp)
                    .fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.Top,
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Spacer(modifier = Modifier.weight(1f))
                    Image(
                        painter = painterResource(pizza.image),
                        contentDescription = "",
                        modifier = Modifier.size(100.dp)
                    )
                    Spacer(modifier = Modifier.weight(1f))
                    if (!favoriteStatus) {
                        Image(
                            painter = painterResource(id = R.drawable.unfavorite),
                            contentDescription = null,
                            modifier = Modifier
                                .size(26.dp)
                                .clickable {
                                    favoriteStatus = !favoriteStatus
                                    onFavoriteClick(pizza)
                                }
                        )
                    } else {
                        Icon(
                            imageVector = Icons.Default.Favorite,
                            tint = RedColor,
                            contentDescription = null,
                            modifier = Modifier
                                .size(26.dp)
                                .clickable {
                                    Toast
                                        .makeText(
                                            context,
                                            "Already added in favorite list",
                                            Toast.LENGTH_SHORT
                                        )
                                        .show()
                                }
                        )
                    }
                }
                SpacerHeight()
                Text(
                    textAlign = TextAlign.Center, text = "${pizza.price}Rs.", style = TextStyle(
                        fontSize = 14.sp, fontWeight = FontWeight.W500, color = RedColor
                    )
                )
                SpacerHeight()
                Text(
                    textAlign = TextAlign.Center, text = pizza.name, style = TextStyle(
                        fontSize = 14.sp, fontWeight = FontWeight.W700, color = DarkBlackColor
                    )
                )
                SpacerHeight()
                Text(
                    textAlign = TextAlign.Center, text = pizza.description, style = TextStyle(
                        fontSize = 11.sp, fontWeight = FontWeight.W400, color = LightGrayColor
                    ), maxLines = 2, minLines = 2,
                    overflow = TextOverflow.Ellipsis
                )
                SpacerHeight()
                Button(
                    onClick = {
                        if (!addToCartStatus) {
                            addToCartStatus = !addToCartStatus
                            addToCart(pizza)
                        } else {
                            Toast.makeText(
                                context,
                                "Already added in cart list",
                                Toast.LENGTH_SHORT
                            ).show()
                        }
                    },
                    shape = RoundedCornerShape(30.dp),
                    colors = ButtonDefaults.buttonColors(containerColor = YellowColor)
                ) {
                    Text(
                        text = "Add", style = TextStyle(
                            fontSize = 13.sp, fontWeight = FontWeight.W600, color = Color.White
                        )
                    )
                }
            }
        }
    }
}