package com.example.jetpackcompose.screen

import android.graphics.Bitmap
import android.net.Uri
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.activity.result.launch
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
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
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material3.*
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.window.Dialog
import coil.compose.rememberAsyncImagePainter
import com.example.jetpackcompose.ui.theme.RedColor
import com.example.jetpackcompose.utilities.STATICS
import com.example.jetpackcompose.view_models.ProfileViewModel

@Composable
fun ProfileScreen(viewModel: ProfileViewModel) {
    val scrollState = rememberScrollState()
    var type by remember { mutableStateOf(STATICS.GALLERY) }
    var profilePicUri by remember { mutableStateOf<Uri?>(null) }
    var profilePicBitmap by remember { mutableStateOf<Bitmap?>(null) }
    val galleryLauncher =
        rememberLauncherForActivityResult(ActivityResultContracts.GetContent()) { uri: Uri? ->
            profilePicUri = uri
        }
    val cameraLauncher =
        rememberLauncherForActivityResult(contract = ActivityResultContracts.TakePicturePreview()) {
            profilePicBitmap = it
        }

    var showDialogStatus by remember { mutableStateOf(false) }
    MyDialog(showDialogStatus = showDialogStatus,
        onDismiss = {
            showDialogStatus = false
        }, onCameraClick = {
            cameraLauncher.launch()
            type = STATICS.CAMERA
        }, onGalleryClick = {
            type = STATICS.GALLERY
            galleryLauncher.launch("image/*")
        })

    var isEditing by remember { mutableStateOf(false) }
    val name by viewModel.name.collectAsState()
    val email by viewModel.email.collectAsState()
    val bio by viewModel.bio.collectAsState()

    var nameInput by remember { mutableStateOf(name) }
    var emailInput by remember { mutableStateOf(email) }
    var bioInput by remember { mutableStateOf(bio) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(start = 16.dp, end = 16.dp, bottom = 16.dp)
            .background(Color.White),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.Start
    ) {
        Column(
            modifier = Modifier
                .verticalScroll(scrollState)
                .weight(1f)
        ) {
            Column(modifier = Modifier.align(Alignment.CenterHorizontally)) {
                Spacer(modifier = Modifier.height(16.dp))
                if (type == STATICS.GALLERY){
                    //  gallery
                    Image(painter = profilePicUri?.let { rememberAsyncImagePainter(it) }
                        ?: painterResource(
                            id = R.drawable.profile_pic
                        ),
                        contentDescription = null,
                        contentScale = ContentScale.Crop,
                        modifier = Modifier
                            .clip(CircleShape)
                            .size(170.dp)
                            .align(Alignment.CenterHorizontally)
                            .clickable {
                                showDialogStatus = true
                            })
                } else {
                    // Camera
                    Image(painter = profilePicBitmap?.let { rememberAsyncImagePainter(it) }
                        ?: painterResource(id = R.drawable.profile_pic),
                        contentDescription = null,
                        contentScale = ContentScale.Crop,
                        modifier = Modifier
                            .clip(CircleShape)
                            .size(170.dp)
                            .align(Alignment.CenterHorizontally)
                            .clickable {
                                showDialogStatus = true
                            })
                }
                Spacer(modifier = Modifier.height(16.dp))
                // Name
                if (isEditing) {
                    BasicTextField(
                        value = nameInput,
                        onValueChange = { nameInput = it },
                        modifier = Modifier
                            .fillMaxWidth()
                            .background(Color.Transparent)
                            .align(Alignment.CenterHorizontally),
                        textStyle = TextStyle(
                            fontSize = 24.sp,
                            fontWeight = FontWeight.Bold,
                            color = Color.Black,
                            textAlign = TextAlign.Center
                        )
                    )
                } else {
                    Text(
                        text = name,
                        style = TextStyle(
                            fontSize = 24.sp,
                            fontWeight = FontWeight.Bold,
                            color = Color.Black
                        ),
                        textAlign = TextAlign.Center,
                        modifier = Modifier
                            .fillMaxWidth()
                            .align(Alignment.CenterHorizontally)
                    )
                }
                Spacer(modifier = Modifier.height(4.dp))
                // Email
                if (isEditing) {
                    BasicTextField(
                        value = emailInput,
                        onValueChange = { emailInput = it },
                        modifier = Modifier.align(Alignment.CenterHorizontally),
                        textStyle = TextStyle(
                            fontSize = 16.sp,
                            fontWeight = FontWeight.Normal,
                            color = Color.Gray,
                            textAlign = TextAlign.Center
                        )
                    )
                } else {
                    Text(
                        text = email,
                        style = TextStyle(
                            fontSize = 16.sp,
                            fontWeight = FontWeight.Normal,
                            color = Color.Gray
                        ),
                        textAlign = TextAlign.Center,
                        modifier = Modifier.align(Alignment.CenterHorizontally)
                    )
                }
            }

            Spacer(modifier = Modifier.height(20.dp))
            // Additional Information
            Text(
                text = "About Me", style = TextStyle(
                    fontSize = 20.sp, fontWeight = FontWeight.SemiBold, color = Color.Black
                )
            )
            Spacer(modifier = Modifier.height(8.dp))
            // Bio
            if (isEditing) {
                BasicTextField(
                    value = bioInput,
                    onValueChange = { bioInput = it },
                    textStyle = TextStyle(
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Normal,
                        color = Color.Black,
                    )
                )
            } else {
                Text(
                    text = bio,
                    style = TextStyle(
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Normal,
                        color = Color.Black
                    )
                )
            }
        }
        Button(
            onClick = {
                if (isEditing) {
                    // Save the edited values
                    if (name != nameInput) {
                        viewModel.updateName(nameInput)
                    }
                    if (email != emailInput) {
                        viewModel.updateEmail(emailInput)
                    }
                    if (bio != bioInput) {
                        viewModel.updateBio(bioInput)
                    }
                }
                // Toggle editing mode
                isEditing = !isEditing
            },
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 5.dp, start = 50.dp, end = 50.dp, bottom = 50.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = RedColor, // Background color
                contentColor = Color.White   // Text color
            ),
        ) {
            Text(text = if (isEditing) "Save" else "Edit Profile")
        }
    }
}

@Composable
fun MyDialog(showDialogStatus: Boolean, onDismiss:() -> Unit, onCameraClick:() -> Unit, onGalleryClick:() -> Unit) {
    if (showDialogStatus) {
        Dialog(onDismissRequest = { onDismiss() }) {
            Surface(
                shape = RoundedCornerShape(8.dp),
                color = MaterialTheme.colorScheme.surface,
                modifier = Modifier.fillMaxWidth()
            ) {
                Column(
                    modifier = Modifier.padding(16.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Row {
                        Image(
                            painter = painterResource(id = R.drawable.camera),
                            contentDescription = null,
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(20.dp)
                                .weight(0.5f).clickable {
                                    onDismiss()
                                    onCameraClick()
                                }
                        )
                        Image(
                            painter = painterResource(id = R.drawable.gallery),
                            contentDescription = null,
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(20.dp)
                                .weight(0.5f).clickable {
                                    onDismiss()
                                    onGalleryClick()
                                }
                        )
                    }
                }
            }
        }
    }
}