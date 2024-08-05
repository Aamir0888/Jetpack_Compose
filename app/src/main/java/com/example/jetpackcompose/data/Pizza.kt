package com.example.jetpackcompose.data

import android.os.Parcelable
import androidx.annotation.DrawableRes
import androidx.compose.runtime.mutableStateListOf
import com.example.jetpackcompose.R
import kotlinx.parcelize.Parcelize

@Parcelize
data class Pizza(
    val id: Int,
    @DrawableRes val image: Int,
    val name: String,
    val description: String,
    val price: Int,
    var item: Int,
    val otherIcons: List<Int>,
    var isFavorite: Boolean = false,
    var isAddedToCart: Boolean = false
) : Parcelable

val pizzaList = listOf(
    Pizza(
        id = 1,
        R.drawable.pizza6,
        "1. Mexican Green Wave",
        "Experience the simplicity of authentic Italian flavors with our Margherita Pizza. This classic features a thin, crispy crust topped with rich tomato sauce, fresh mozzarella cheese, and fragrant basil leaves, drizzled with olive oil for a perfect finish.",
        100,
        1,
        listOf(
            R.drawable.two,
            R.drawable.pizza2,
            R.drawable.pizza3,
            R.drawable.pizza4,
            R.drawable.pizza6
        )
    ),
    Pizza(
        id = 2,
        R.drawable.two,
        "2. Fresh Farm House",
        "Dive into our Pepperoni Feast Pizza, loaded with slices of spicy pepperoni and a blend of mozzarella and cheddar cheeses. Each bite offers a savory and slightly spicy experience that will satisfy meat lovers' cravings.",
        90,
        1,
        listOf(
            R.drawable.pizza6,
            R.drawable.two,
            R.drawable.pizza2,
            R.drawable.pizza3,
            R.drawable.pizza4
        )
    ),
    Pizza(
        id = 3,
        R.drawable.three,
        "3. Peppy Paneer",
        "Enjoy a burst of garden-fresh flavors with our Veggie Supreme Pizza. This colorful creation is adorned with bell peppers, onions, mushrooms, black olives, and juicy tomatoes, all nestled on a bed of gooey cheese and tangy tomato sauce.",
        130,
        1,
        listOf(
            R.drawable.pizza3,
            R.drawable.pizza5,
            R.drawable.pizza6,
            R.drawable.four,
            R.drawable.three
        )
    ),
    Pizza(
        id = 4,
        R.drawable.four,
        "4. Mexican Green Wave",
        "Our BBQ Chicken Delight Pizza combines the smoky taste of grilled chicken with tangy barbecue sauce, red onions, and fresh cilantro. This savory and sweet combination is perfect for barbecue enthusiasts looking for a pizza twist.",
        120,
        1,
        listOf(
            R.drawable.two,
            R.drawable.pizza2,
            R.drawable.four,
            R.drawable.pizza5,
            R.drawable.three
        )
    ),
    Pizza(
        id = 5,
        R.drawable.three,
        "5. Fresh Farm House",
        "Discover the tropical taste of our Hawaiian Paradise Pizza, featuring juicy pineapple chunks paired with savory ham. This unique blend of sweet and salty flavors is enhanced by a generous layer of melted mozzarella cheese.",
        128,
        1,
        listOf(
            R.drawable.two,
            R.drawable.pizza2,
            R.drawable.pizza3,
            R.drawable.pizza4,
            R.drawable.three
        )
    ),
    Pizza(
        id = 6,
        R.drawable.pizza2,
        "6. Mexican Green Wave",
        "Heat seekers will love our Spicy Inferno Pizza, which features fiery jalapeños, spicy sausage, and a dash of hot pepper flakes. This pizza is crafted to deliver a deliciously intense experience with each bite.",
        95,
        1,
        listOf(
            R.drawable.pizza3,
            R.drawable.pizza5,
            R.drawable.pizza6,
            R.drawable.four,
            R.drawable.three
        )
    ),
    Pizza(
        id = 7,
        R.drawable.three,
        "7. Peppy Paneer",
        "Indulge in the hearty flavors of our Steakhouse Special Pizza. Tender slices of steak, roasted peppers, and onions rest on a crispy crust, topped with a rich blend of cheeses for a satisfying, mouthwatering meal.",
        113,
        1,
        listOf(
            R.drawable.three,
            R.drawable.two,
            R.drawable.pizza2,
            R.drawable.pizza3,
            R.drawable.pizza4
        )
    ),
    Pizza(
        id = 8,
        R.drawable.four,
        "8. Special Pizza House",
        "Dive into our Pepperoni Feast Pizza, loaded with slices of spicy pepperoni and a blend of mozzarella and cheddar cheeses. Each bite offers a savory and slightly spicy experience that will satisfy meat lovers' cravings.",
        134,
        1,
        listOf(
            R.drawable.pizza3,
            R.drawable.pizza5,
            R.drawable.pizza6,
            R.drawable.four,
            R.drawable.three
        )
    ),
    Pizza(
        id = 9,
        R.drawable.pizza5,
        "9. Fresh Farm House",
        "Take a culinary journey with our Mediterranean Magic Pizza. Feta cheese, black olives, sun-dried tomatoes, and spinach come together on a creamy garlic sauce base, delivering a taste of the Mediterranean",
        97,
        1,
        listOf(R.drawable.two, R.drawable.pizza2, R.drawable.pizza3, R.drawable.pizza4)
    ),
    Pizza(
        id = 10,
        R.drawable.three,
        "10. Peppy Paneer",
        "Our Seafood Sensation Pizza is a seafood lover's dream, combining shrimp, crab, and scallops on a garlic-butter base. Topped with mozzarella cheese and fresh herbs, this pizza offers a rich and luxurious flavor profile.",
        136,
        1,
        listOf(
            R.drawable.pizza3,
            R.drawable.pizza5,
            R.drawable.pizza6,
            R.drawable.four,
            R.drawable.three
        )
    ),
    Pizza(
        id = 11,
        R.drawable.pizza3,
        "11. Fresh Farm House",
        "Take a culinary journey with our Mediterranean Magic Pizza. Feta cheese, black olives, sun-dried tomatoes, and spinach come together on a creamy garlic sauce base, delivering a taste of the Mediterranean.",
        107,
        1,
        listOf(
            R.drawable.two,
            R.drawable.pizza2,
            R.drawable.pizza3,
            R.drawable.pizza4,
            R.drawable.pizza
        )
    ),
    Pizza(
        id = 12,
        R.drawable.pizza3,
        "12. Mexican Green Wave",
        "Take a culinary journey with our Mediterranean Magic Pizza. Feta cheese, black olives, sun-dried tomatoes, and spinach come together on a creamy garlic sauce base, delivering a taste of the Mediterranean.",
        107,
        1,
        listOf(
            R.drawable.two,
            R.drawable.pizza2,
            R.drawable.pizza3,
            R.drawable.pizza4,
            R.drawable.pizza
        )
    )
)
