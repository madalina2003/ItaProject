package com.tus.tusparking

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowLeft
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
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

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale

import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.tus.tusparking.ui.theme.TUSParkingTheme


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SearchScreen(navController: NavController) {

        var searchQuery by remember { mutableStateOf("") }


    Column(
        modifier = Modifier
            .padding(dimensionResource(R.dimen.padding_home_columnA))
            .fillMaxSize()
            .background(Color.LightGray)
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

        Spacer(modifier = Modifier.height(15.dp))
        IconButton(
            onClick = { navController.popBackStack() },
            modifier = Modifier
                .padding(start = 0.dp)
        ) {
            Icon(
                imageVector = Icons.Filled.KeyboardArrowLeft,
                modifier = Modifier.size(25.dp),
                contentDescription = stringResource(R.string.sign_up_screen_back_button)
            )
        }
        OutlinedTextField(
            value = searchQuery,
            singleLine = true,
            shape = MaterialTheme.shapes.medium,
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = dimensionResource(R.dimen.text_field_horizontal_padding))
                .background(Color.White),
            onValueChange = { searchQuery = it },
            label = { Text("Search", fontSize = 20.sp) },
            keyboardOptions = KeyboardOptions.Default,
            keyboardActions = KeyboardActions.Default,
            leadingIcon = {
                Icon(
                    painter = painterResource(R.drawable.icon),
                    contentDescription = null,
                    modifier = Modifier.size(18.dp)
                )
            },
            trailingIcon = {
                Icon(
                    painter = painterResource(R.drawable.search),
                    contentDescription = null,
                    modifier = Modifier
                        .clickable { navController.navigate("Maps") }
                        .size(18.dp)

                )
            }

        )

    }

}



@Preview(showBackground = true)
@Composable
fun SearchScreenPreview() {
    TUSParkingTheme {
        val navController = rememberNavController()
        SearchScreen(navController)
    }
}


@Preview
@Composable
fun SearchScreenDarkPreview() {
    TUSParkingTheme(darkTheme = true) {
        val navController = rememberNavController()
        SearchScreen(navController)
    }
}