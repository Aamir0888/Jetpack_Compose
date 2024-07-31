package com.example.jetpackcompose.data

import androidx.annotation.DrawableRes
import com.example.jetpackcompose.R

data class Pizza(
    @DrawableRes val image:Int,
    val name:String,
    val description:String,
    val price:String,
    val otherIcons: List<Int>
)

val pizzaList = listOf(
    Pizza(
        R.drawable.pizza6,
        "Fresh Farm House",
        "crisp capsicum, succulent mushrooms and fresh tomatoes",
        "£14.25",
        listOf(R.drawable.two, R.drawable.pizza2, R.drawable.pizza3, R.drawable.pizza4, R.drawable.pizza6)
    ),
    Pizza(
        R.drawable.two,
        "Fresh Farm House",
        "crisp capsicum, succulent mushrooms and fresh tomatoes",
        "£14.25",
        listOf(R.drawable.pizza6, R.drawable.two, R.drawable.pizza2, R.drawable.pizza3, R.drawable.pizza4)
    ),
    Pizza(
        R.drawable.three,
        "Peppy Paneer",
        "Chunky paneer with crisp capsicum and spicy red pepper",
        "£16.75",
        listOf(R.drawable.pizza3, R.drawable.pizza5, R.drawable.pizza6, R.drawable.four, R.drawable.three)
    ),
    Pizza(
        R.drawable.four,
        "Mexican Green Wave",
        "A pizza loaded with crunchy onions, crisp capsicum, juicy tomatoes",
        "£10.25",
        listOf(R.drawable.two, R.drawable.pizza2, R.drawable.four, R.drawable.pizza5, R.drawable.three)
    ),
    Pizza(
        R.drawable.three,
        "Fresh Farm House",
        "crisp capsicum, succulent mushrooms and fresh tomatoes",
        "£14.25",
        listOf(R.drawable.two, R.drawable.pizza2, R.drawable.pizza3, R.drawable.pizza4, R.drawable.three)
    ),
    Pizza(
        R.drawable.pizza2,
        "Fresh Farm House",
        "crisp capsicum, succulent mushrooms and fresh tomatoes",
        "£14.25",
        listOf(R.drawable.pizza3, R.drawable.pizza5, R.drawable.pizza6, R.drawable.four, R.drawable.three)
    ),
    Pizza(
        R.drawable.three,
        "Peppy Paneer",
        "Chunky paneer with crisp capsicum and spicy red pepper",
        "£19.25",
        listOf(R.drawable.three, R.drawable.two, R.drawable.pizza2, R.drawable.pizza3, R.drawable.pizza4)
    ),
    Pizza(
        R.drawable.four,
        "Fresh Farm House",
        "crisp capsicum, succulent mushrooms and fresh tomatoes",
        "£14.25",
        listOf(R.drawable.pizza3, R.drawable.pizza5, R.drawable.pizza6, R.drawable.four, R.drawable.three)
    ),
    Pizza(
        R.drawable.pizza5,
        "Fresh Farm House",
        "crisp capsicum, succulent mushrooms and fresh tomatoes",
        "£14.25",
        listOf(R.drawable.two, R.drawable.pizza2, R.drawable.pizza3, R.drawable.pizza4)
    ),
    Pizza(
        R.drawable.three,
        "Peppy Paneer",
        "Chunky paneer with crisp capsicum and spicy red pepper",
        "£19.25",
        listOf(R.drawable.pizza3, R.drawable.pizza5, R.drawable.pizza6, R.drawable.four, R.drawable.three)
    ),
    Pizza(
        R.drawable.pizza3,
        "Fresh Farm House",
        "crisp capsicum, succulent mushrooms and fresh tomatoes",
        "£14.25",
        listOf(R.drawable.two, R.drawable.pizza2, R.drawable.pizza3, R.drawable.pizza4, R.drawable.pizza)
    )
)
