package com.tus.tusparking

import androidx.compose.foundation.Image
import androidx.compose.foundation.background

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowLeft
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.google.android.gms.maps.model.CameraPosition
import com.google.android.gms.maps.model.LatLng
import com.google.maps.android.compose.GoogleMap
import com.google.maps.android.compose.Marker
import com.google.maps.android.compose.MarkerState
import com.google.maps.android.compose.rememberCameraPositionState
import androidx.lifecycle.viewmodel.compose.viewModel
import com.tus.tusparking.ui.theme.TUSParkingTheme




@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MapScreen(navController: NavController, userId: String?, viewModel: HomeScreenViewModel = viewModel()) {
    Text(text = "User ID: $userId")

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
                .background(Color.Black)
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

    }
    Scaffold(
        // topBar = {MapTopAppBar(navController)}

    ) { paddingValues ->
        Column(
            modifier = Modifier
                .padding(paddingValues)
                .verticalScroll(rememberScrollState()),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Column {
                Spacer(modifier = Modifier.height(50.dp))
                //   MapScreenImage(navController)


            }

        }

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(dimensionResource(R.dimen.rectangle_height))
                .background(Color.Black)
        ) {

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



        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(top = dimensionResource(R.dimen.padding_top_image))
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 16.dp, top = 130.dp)
                    .height(56.dp),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                // Back button
                IconButton(
                    onClick = { navController.popBackStack() },
                    modifier = Modifier
                        .padding(end = 16.dp)
                ) {
                    Icon(
                        imageVector = Icons.Filled.KeyboardArrowLeft,
                        modifier = Modifier.size(25.dp),
                        contentDescription = stringResource(R.string.sign_up_screen_back_button)
                    )
                }
                // Search icon
                Button(
                    onClick = {

                        navController.navigate("Search")
                    },
                    modifier = Modifier

                ) {
                    Icon(
                        imageVector = Icons.Filled.Search,
                        modifier = Modifier.size(25.dp),
                        contentDescription = stringResource(R.string.search)
                    )
                }
                Button(
                    onClick = {
                      //  logoutUser()
                        navController.popBackStack()
                    }
                ) {
                    Text(text = "Logout")
                }

            }

        }
        //Google map
        Box(
            modifier = Modifier
                .width(1000.dp)
                .height(1500.dp)
                .padding(vertical = 250.dp)
                .wrapContentSize(align = Alignment.Center)
        ) {
            val tus = LatLng(52.67566, -8.64752)
            val cameraPositionState = rememberCameraPositionState {
                position = CameraPosition.fromLatLngZoom(tus, 16f)
            }
            GoogleMap(
                modifier = Modifier.fillMaxSize(),
                cameraPositionState = cameraPositionState
            ) {
                Marker(
                    state = MarkerState(position = tus),
                    title = "TUS",
                    snippet = "Marker in TUS"
                )
           }
        }
    }

        }









@Preview(showBackground = true)
@Composable
fun MapScreenPreview() {
    TUSParkingTheme {
        val navController = rememberNavController()
       MapScreen(navController,"data")

           // MapTopAppBar(navController)
     //   MapScreenImage(navController)

    }


}


