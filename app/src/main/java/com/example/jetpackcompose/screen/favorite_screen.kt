package com.example.jetpackcompose.screen

import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.DismissDirection
import androidx.compose.material.DismissValue
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Icon
import androidx.compose.material.SwipeToDismiss
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.rememberDismissState
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
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
import com.example.jetpackcompose.room_db.PizzaEntity
import com.example.jetpackcompose.ui.theme.DarkBlackColor
import com.example.jetpackcompose.ui.theme.RedColor
import com.example.jetpackcompose.utilities.STATICS
import com.example.jetpackcompose.view_models.PizzaViewModel

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun FavoriteScreen(pizzaViewModel: PizzaViewModel) {
    val pizzaList = pizzaViewModel.favoriteItems.collectAsState().value
    LazyColumn(
        modifier = Modifier.padding(horizontal = 5.dp),
        contentPadding = PaddingValues(bottom = 65.dp)
    ){
        itemsIndexed(items = pizzaList, key = { _, listItem ->
            listItem.hashCode()
        })
        { _, item ->
            val state = rememberDismissState(
                confirmStateChange = {
                    if (it == DismissValue.DismissedToStart) {
                        pizzaViewModel.deletePizzaByIdStatus(item.pizzaId, STATICS.FAVORITE)
                    }
                    true
                }
            )
            SwipeToDismiss(state = state, background = {
                val color = when (state.dismissDirection) {
                    DismissDirection.StartToEnd -> Color.Transparent
                    DismissDirection.EndToStart -> Color.Transparent
                    null -> Color.Transparent
                }
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(color = color)
                        .padding(15.dp)
                ) {
                    Icon(
                        imageVector = Icons.Default.Delete,
                        contentDescription = "Delete",
                        tint = Color.Black,
                        modifier = Modifier
                            .align(Alignment.CenterEnd)
                            .size(40.dp)
                    )
                }
            }, dismissContent = {
                PizzaFavoriteSingleItem(pizza = item)
            }, directions = setOf(DismissDirection.EndToStart))
        }
    }
}

@Composable
fun PizzaFavoriteSingleItem(pizza: PizzaEntity) {
    Card(
        modifier = Modifier
            .padding(horizontal = 5.dp, vertical = 8.dp),
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
                    textAlign = TextAlign.Start, text = "${pizza.price}Rs.", style = TextStyle(
                        fontSize = 14.sp, fontWeight = FontWeight.W500, color = RedColor
                    )
                )
            }
            SpacerWidth(10.dp)
            Icon(
                imageVector = Icons.Default.Favorite,
                tint = RedColor,
                contentDescription = null,
                modifier = Modifier
                    .size(26.dp)
            )
        }
    }
}