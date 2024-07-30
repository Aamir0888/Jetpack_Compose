package com.example.jetpackcompose.screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.jetpackcompose.R
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.ui.text.TextStyle
import com.example.jetpackcompose.ui.theme.RedColor

@Composable
fun ProfileScreen() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
            .background(Color.White),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.Start
    ) {
        // Profile picture
        Column(modifier = Modifier.align(Alignment.CenterHorizontally)) {
            Image(
                painter = painterResource(id = R.drawable.profile_pic),
                contentDescription = null,
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .clip(CircleShape)
                    .size(170.dp)
            )
            Spacer(modifier = Modifier.height(16.dp))
            // Name
            Text(
                text = "Aamir Khan",
                style = TextStyle(
                    fontSize = 24.sp, fontWeight = FontWeight.Bold, color = Color.Black
                ),
                textAlign = TextAlign.Center,
                modifier = Modifier.align(Alignment.CenterHorizontally)
            )
            Spacer(modifier = Modifier.height(6.dp))
            // Email
            Text(
                text = "johndoe@example.com",
                style = TextStyle(
                    fontSize = 16.sp, fontWeight = FontWeight.Normal, color = Color.Gray
                ),
                textAlign = TextAlign.Center,
                modifier = Modifier.align(Alignment.CenterHorizontally)
            )
        }

        Spacer(modifier = Modifier.height(20.dp))
        // Additional Information
        Text(
            text = "About Me", style = TextStyle(
                fontSize = 20.sp, fontWeight = FontWeight.SemiBold, color = Color.Black
            )
        )
        Spacer(modifier = Modifier.height(8.dp))
        Text(
            text = "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Integer nec odio. Praesent libero. Sed cursus ante dapibus diam.",
            style = TextStyle(
                fontSize = 16.sp, fontWeight = FontWeight.Normal, color = Color.Black
            )
        )
        Spacer(modifier = Modifier.height(10.dp))
        // Edit Profile Button

        Box(contentAlignment = Alignment.BottomCenter, modifier = Modifier.fillMaxHeight().padding(bottom = 60.dp)) {
            Button(
                onClick = {

                },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 16.dp, start = 50.dp, end = 50.dp, bottom = 10.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = RedColor, // Background color
                    contentColor = Color.White   // Text color
                ),
            ) {
                Text(text = "Edit Profile")
            }
        }
    }
}