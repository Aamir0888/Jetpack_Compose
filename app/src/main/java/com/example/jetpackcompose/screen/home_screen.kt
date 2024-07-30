package com.example.jetpackcompose.screen

import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.jetpackcompose.R
import com.example.jetpackcompose.common.SpacerHeight
import com.example.jetpackcompose.data.Pizza
import com.example.jetpackcompose.data.pizzaList
import com.example.jetpackcompose.ui.theme.DarkBlackColor
import com.example.jetpackcompose.ui.theme.LightGrayColor
import com.example.jetpackcompose.ui.theme.RedColor
import com.example.jetpackcompose.ui.theme.YellowColor

@Composable
fun HomeScreen() {
    LazyVerticalGrid(
        columns = GridCells.Fixed(2),
        modifier = Modifier.padding(horizontal = 5.dp),
        contentPadding = PaddingValues(bottom = 60.dp)
    ) {
        items(pizzaList.size) { index ->
            PizzaSingleItem(pizza = pizzaList[index])
        }
    }
}

@Composable
fun PizzaSingleItem(pizza: Pizza) {
    val context = LocalContext.current
    Card(
        modifier = Modifier.padding(5.dp), shape = RoundedCornerShape(15.dp)
    ) {
        Box(modifier = Modifier.fillMaxWidth()) {
            Column(
                modifier = Modifier
                    .padding(8.dp)
                    .fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                Image(
                    painter = painterResource(id = R.drawable.pizza),
                    contentDescription = "",
                    modifier = Modifier.size(100.dp)
                )
                SpacerHeight()
                Text(
                    textAlign = TextAlign.Center, text = pizza.price, style = TextStyle(
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
                    )
                )
                SpacerHeight()
                Button(
                    onClick = {
                        Toast.makeText(context, "This item added in cart", Toast.LENGTH_SHORT)
                            .show()
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