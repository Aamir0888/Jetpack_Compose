package com.example.jetpackcompose.screen

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.PathEffect
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.jetpackcompose.R
import com.example.jetpackcompose.common.SpacerHeight
import com.example.jetpackcompose.common.SpacerWidth
import com.example.jetpackcompose.data.Pizza
import com.example.jetpackcompose.data.pizzaList
import com.example.jetpackcompose.ui.theme.DarkBlackColor
import com.example.jetpackcompose.ui.theme.RedColor

@Composable
fun CartScreen() {
    LazyColumn(
        modifier = Modifier.padding(horizontal = 5.dp),
        contentPadding = PaddingValues(bottom = 65.dp)
    ) {
        items(pizzaList) { item ->
            PizzaCartSingleItem(pizza = item, onClick = {

            })
        }

        item {
            Column(modifier = Modifier.padding(horizontal = 8.dp)) {
                SpacerHeight(20.dp)
                DottedLine(color = Color.Black)
                SpacerHeight(20.dp)
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Text(
                        textAlign = TextAlign.Start, text = "Total", style = TextStyle(
                            fontSize = 16.sp, fontWeight = FontWeight.W500, color = DarkBlackColor
                        )
                    )
                    Text(
                        textAlign = TextAlign.Start, text = "500Rs", style = TextStyle(
                            fontSize = 16.sp, fontWeight = FontWeight.W500, color = DarkBlackColor
                        )
                    )
                }
                SpacerHeight(20.dp)
                DottedLine(color = Color.Black)
                SpacerHeight(25.dp)
                Button(
                    onClick = {

                    }, modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 30.dp),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = RedColor, // Background color
                        contentColor = Color.White   // Text color
                    )
                ) {
                    Text(text = "Proceed to checkout")
                }
            }
        }
    }
}

@Composable
fun PizzaCartSingleItem(pizza: Pizza, onClick: () -> Unit) {
    var items by rememberSaveable { mutableIntStateOf(1) }
    val context = LocalContext.current
    Card(
        modifier = Modifier
            .padding(horizontal = 5.dp, vertical = 8.dp)
            .clickable { onClick() },
        shape = RoundedCornerShape(15.dp)
    ) {
        Row(
            modifier = Modifier
                .padding(8.dp)
                .fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Image(
                painter = painterResource(pizza.image),
                contentDescription = null,
                modifier = Modifier.size(80.dp)
            )
            SpacerWidth(10.dp)
            Column(
                modifier = Modifier.weight(1f),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.Start
            ) {
                Text(
                    textAlign = TextAlign.Start, text = pizza.name, style = TextStyle(
                        fontSize = 16.sp, fontWeight = FontWeight.W700, color = DarkBlackColor
                    ), maxLines = 2, overflow = TextOverflow.Ellipsis
                )
                SpacerHeight()
                Text(
                    textAlign = TextAlign.Start, text = pizza.price, style = TextStyle(
                        fontSize = 14.sp, fontWeight = FontWeight.W500, color = RedColor
                    )
                )
            }
            SpacerWidth(10.dp)
            Card(
                shape = RoundedCornerShape(5.dp)
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Image(painter = painterResource(id = R.drawable.minus),
                        contentDescription = null,
                        modifier = Modifier
                            .size(24.dp)
                            .clickable {
                                items--
                            })
                    androidx.compose.material.Text(
                        text = items.toString(), style = TextStyle(
                            fontSize = 18.sp,
                            fontWeight = FontWeight.W600,
                            color = Color.Black,
                            textAlign = TextAlign.Center
                        ), modifier = Modifier.width(30.dp)
                    )
                    Image(painter = painterResource(id = R.drawable.add),
                        contentDescription = null,
                        modifier = Modifier
                            .size(24.dp)
                            .clickable {
                                items++
                            })
                }
            }
        }
    }
}

@Composable
fun DottedLine(color: Color) {
    val pathEffect = PathEffect.dashPathEffect(floatArrayOf(10f, 10f), 0f)
    Canvas(
        Modifier
            .fillMaxWidth()
            .height(2.dp)
    ) {
        drawLine(
            color = color,
            start = Offset(0f, 0f),
            end = Offset(size.width, 0f),
            pathEffect = pathEffect
        )
    }
}