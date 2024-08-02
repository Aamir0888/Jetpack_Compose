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
        "Experience the simplicity of authentic Italian flavors with our Margherita Pizza. This classic features a thin, crispy crust topped with rich tomato sauce, fresh mozzarella cheese, and fragrant basil leaves, drizzled with olive oil for a perfect finish.",
        "£14.25",
        listOf(R.drawable.two, R.drawable.pizza2, R.drawable.pizza3, R.drawable.pizza4, R.drawable.pizza6)
    ),
    Pizza(
        R.drawable.two,
        "Fresh Farm House",
        "Dive into our Pepperoni Feast Pizza, loaded with slices of spicy pepperoni and a blend of mozzarella and cheddar cheeses. Each bite offers a savory and slightly spicy experience that will satisfy meat lovers' cravings.",
        "£14.25",
        listOf(R.drawable.pizza6, R.drawable.two, R.drawable.pizza2, R.drawable.pizza3, R.drawable.pizza4)
    ),
    Pizza(
        R.drawable.three,
        "Peppy Paneer",
        "Enjoy a burst of garden-fresh flavors with our Veggie Supreme Pizza. This colorful creation is adorned with bell peppers, onions, mushrooms, black olives, and juicy tomatoes, all nestled on a bed of gooey cheese and tangy tomato sauce.",
        "£16.75",
        listOf(R.drawable.pizza3, R.drawable.pizza5, R.drawable.pizza6, R.drawable.four, R.drawable.three)
    ),
    Pizza(
        R.drawable.four,
        "Mexican Green Wave",
        "Our BBQ Chicken Delight Pizza combines the smoky taste of grilled chicken with tangy barbecue sauce, red onions, and fresh cilantro. This savory and sweet combination is perfect for barbecue enthusiasts looking for a pizza twist.",
        "£10.25",
        listOf(R.drawable.two, R.drawable.pizza2, R.drawable.four, R.drawable.pizza5, R.drawable.three)
    ),
    Pizza(
        R.drawable.three,
        "Fresh Farm House",
        "Discover the tropical taste of our Hawaiian Paradise Pizza, featuring juicy pineapple chunks paired with savory ham. This unique blend of sweet and salty flavors is enhanced by a generous layer of melted mozzarella cheese.",
        "£14.25",
        listOf(R.drawable.two, R.drawable.pizza2, R.drawable.pizza3, R.drawable.pizza4, R.drawable.three)
    ),
    Pizza(
        R.drawable.pizza2,
        "Fresh Farm House",
        "Heat seekers will love our Spicy Inferno Pizza, which features fiery jalapeños, spicy sausage, and a dash of hot pepper flakes. This pizza is crafted to deliver a deliciously intense experience with each bite.",
        "£14.25",
        listOf(R.drawable.pizza3, R.drawable.pizza5, R.drawable.pizza6, R.drawable.four, R.drawable.three)
    ),
    Pizza(
        R.drawable.three,
        "Peppy Paneer",
        "Indulge in the hearty flavors of our Steakhouse Special Pizza. Tender slices of steak, roasted peppers, and onions rest on a crispy crust, topped with a rich blend of cheeses for a satisfying, mouthwatering meal.",
        "£19.25",
        listOf(R.drawable.three, R.drawable.two, R.drawable.pizza2, R.drawable.pizza3, R.drawable.pizza4)
    ),
    Pizza(
        R.drawable.four,
        "Fresh Farm House",
        "Dive into our Pepperoni Feast Pizza, loaded with slices of spicy pepperoni and a blend of mozzarella and cheddar cheeses. Each bite offers a savory and slightly spicy experience that will satisfy meat lovers' cravings.",
        "£14.25",
        listOf(R.drawable.pizza3, R.drawable.pizza5, R.drawable.pizza6, R.drawable.four, R.drawable.three)
    ),
    Pizza(
        R.drawable.pizza5,
        "Fresh Farm House",
        "Take a culinary journey with our Mediterranean Magic Pizza. Feta cheese, black olives, sun-dried tomatoes, and spinach come together on a creamy garlic sauce base, delivering a taste of the Mediterranean",
        "£14.25",
        listOf(R.drawable.two, R.drawable.pizza2, R.drawable.pizza3, R.drawable.pizza4)
    ),
    Pizza(
        R.drawable.three,
        "Peppy Paneer",
        "Our Seafood Sensation Pizza is a seafood lover's dream, combining shrimp, crab, and scallops on a garlic-butter base. Topped with mozzarella cheese and fresh herbs, this pizza offers a rich and luxurious flavor profile.",
        "£19.25",
        listOf(R.drawable.pizza3, R.drawable.pizza5, R.drawable.pizza6, R.drawable.four, R.drawable.three)
    ),
    Pizza(
        R.drawable.pizza3,
        "Fresh Farm House",
        "Take a culinary journey with our Mediterranean Magic Pizza. Feta cheese, black olives, sun-dried tomatoes, and spinach come together on a creamy garlic sauce base, delivering a taste of the Mediterranean.",
        "£14.25",
        listOf(R.drawable.two, R.drawable.pizza2, R.drawable.pizza3, R.drawable.pizza4, R.drawable.pizza)
    )
)
