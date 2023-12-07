package com.tus.tusparking

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.ClickableText
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.BlendMode.Companion.Color
import androidx.compose.ui.graphics.BlendMode.Companion.Screen
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.tus.tusparking.ui.theme.TUSParkingTheme






@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(navController: NavController) {
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .padding(dimensionResource(R.dimen.padding_home_columnA))
            .fillMaxSize()
            .verticalScroll(rememberScrollState()),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // Black rectangle with image inside
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(dimensionResource(R.dimen.rectangle_height))
                .background(androidx.compose.ui.graphics.Color.Black)
        ) {
            // Image - TUS LOGO
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(top = dimensionResource(R.dimen.padding_top_image))
            ) {
                Image(
                    painter = painterResource(R.drawable.tus),
                    contentDescription = stringResource(R.string.home_screen_image_content),
                    contentScale = ContentScale.Fit,
                    modifier = Modifier
                        .fillMaxSize()
                )
            }
        }

        // Text
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            Column {
                Row {
                    Text(
                        text = stringResource(R.string.home_screen_text),
                        fontWeight = FontWeight.Bold,
                        fontSize = 40.sp,
                    )
                }
            }
            Spacer(modifier = Modifier.height(15.dp))
            Column() {
                Text(
                    text = stringResource(R.string.home_screen_textMessage),
                    textAlign = TextAlign.Center,
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Normal
                )
            }
            Spacer(modifier = Modifier.height(16.dp))
        }

        Spacer(modifier = Modifier.height(70.dp))

        // Embedding OutlinedTextField components
        OutlinedTextField(
            value = email,
            singleLine = true,
            shape = MaterialTheme.shapes.medium,
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = dimensionResource(R.dimen.text_field_horizontal_padding)),
            onValueChange = { email = it },
            colors = TextFieldDefaults.outlinedTextFieldColors(
                focusedBorderColor = MaterialTheme.colorScheme.surface,
                unfocusedBorderColor = MaterialTheme.colorScheme.surface,
                disabledBorderColor = MaterialTheme.colorScheme.surface,
            ),
            label = { Text(stringResource(R.string.sign_up_screen_email), fontSize = 20.sp,) },
            isError = false,
            keyboardOptions = KeyboardOptions.Default,
            keyboardActions = KeyboardActions.Default,
        )
        Spacer(modifier = Modifier.height(15.dp))

        OutlinedTextField(
            value = password,
            singleLine = true,
            shape = MaterialTheme.shapes.medium,
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = dimensionResource(R.dimen.text_field_horizontal_padding)),
            onValueChange = { password = it },
            colors = TextFieldDefaults.outlinedTextFieldColors(
                focusedBorderColor = MaterialTheme.colorScheme.surface,
                unfocusedBorderColor = MaterialTheme.colorScheme.surface,
                disabledBorderColor = MaterialTheme.colorScheme.surface,
            ),
            label = { Text(stringResource(R.string.sign_up_screen_password), fontSize = 20.sp,) },
            isError = false,
            keyboardOptions = KeyboardOptions.Default,
            keyboardActions = KeyboardActions.Default,
            visualTransformation = PasswordVisualTransformation()
        )
        Spacer(modifier = Modifier.height(15.dp))
    }
    Column(
        modifier = Modifier
            .padding(
                start = dimensionResource(R.dimen.padding_sign_up_button_start),
                end = dimensionResource(R.dimen.padding_sign_up_button_end)
            )
    ) {
        Spacer(modifier = Modifier.height(550.dp))
        Button(
            onClick = {
                navController.navigate("Maps")
            },
            shape = MaterialTheme.shapes.medium,
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(
                text = stringResource(R.string.sign_in_screen_signIn_button),
                fontWeight = FontWeight.Normal,
                fontSize = 18.sp,
                textAlign = TextAlign.Center
            )
        }
    }

    Text(

        buildAnnotatedString {
            withStyle(
                style = SpanStyle(
                    color = androidx.compose.ui.graphics.Color.Blue,
                    fontSize = 16.sp
                ), {
                    append("SignUp?")
                }
            )
        },
        modifier = Modifier
            .padding(top = 600.dp)
            .fillMaxWidth()
            .clickable {
                navController.navigate("SignScreen")
            }
            .padding(160.dp, 20.dp, 0.dp, 0.dp)

    )
}

@Preview(showBackground = true)
@Composable
fun HomeScreenPreview() {
    TUSParkingTheme {
        val navController = rememberNavController()
        HomeScreen(navController)
    }
}


@Preview
@Composable
fun HomeScreenDarkPreview() {
    TUSParkingTheme(darkTheme = true) {
        val navController = rememberNavController()
        HomeScreen(navController)
    }
}