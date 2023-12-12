package com.tus.tusparking

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.google.accompanist.permissions.isGranted
import com.google.accompanist.permissions.rememberPermissionState
import com.google.android.gms.maps.GoogleMapOptions
import com.google.android.gms.maps.MapFragment
import com.google.android.gms.maps.SupportMapFragment
import android.Manifest
import android.Manifest.permission.ACCESS_FINE_LOCATION
import android.content.pm.PackageManager
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.IconButton
import androidx.compose.ui.platform.LocalContext
import androidx.core.app.NotificationCompat
import androidx.core.content.ContextCompat
import androidx.lifecycle.viewmodel.compose.viewModel
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices

import com.google.firebase.FirebaseApp
import com.google.maps.android.compose.Marker
import com.google.maps.android.compose.MarkerState
import com.tus.tusparking.ui.theme.TUSParkingTheme



class MainActivity : ComponentActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
       // FirebaseApp.initializeApp(this)

        setContent {
            TUSParkingTheme {

                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                   TUSParkingApp()
                }



            }

            }
        }
    }


@OptIn(ExperimentalPermissionsApi::class)
@Composable
fun TUSParkingApp(startDestination:String=Screen.SignUpScreen.route) {
    val navController = rememberNavController()
    val viewModel: HomeScreenViewModel = viewModel()
    TUSParkingTheme {
        Surface(
            modifier = androidx.compose.ui.Modifier.fillMaxSize(),
            color = MaterialTheme.colorScheme.background
        ) {

            NavHost(navController = navController, startDestination = startDestination) {

                    composable(Screen.SignUpScreen.route) {
                        SignScreen(navController)
                    }
                    composable(Screen.HomeScreen.route) {
                        HomeScreen(navController)
                    }
//                    composable("Maps") {
//                        MapScreen(navController)
//                    }
                composable(Screen.Search.route) {
                    SearchScreen(navController)
                }

                composable("maps/{userId}") { backStackEntry ->
                    val userId = backStackEntry.arguments?.getString("userId")
                    MapScreen(navController, userId)
                }

            }
//            NavHost(navController = navController, startDestination = "Maps") {
//                composable("Search"){
//                    SearchScreen(navController)
//                }
//            }

            val context = LocalContext.current

            val postNotificationPermission=
                rememberPermissionState(permission = Manifest.permission.POST_NOTIFICATIONS)
            val notificationService=NotificationService(context)
            LaunchedEffect(postNotificationPermission ){
                if(!postNotificationPermission.status.isGranted){
                    postNotificationPermission.launchPermissionRequest()
                }
            }
            Column {
                Button(
                    modifier = Modifier
                        .size(100.dp, 40.dp),
                    onClick = {
                        notificationService.showBasicNotification()
                    }
                ) {
                    Text(text = "Show basic notifications")
                }
                Spacer(modifier = Modifier.height(10.dp))

                Button(
                    modifier = Modifier
                        .size(120.dp, 40.dp),
                    onClick = {
                        notificationService.showExpandableNotification()
                    }
                ) {
                    Text(text = "Show Expandable notifications")
                }
            }


            }
        }

    }


@Preview(showBackground = true)
@Composable
fun TUSParkingAppPreview() {
    TUSParkingApp()
}

