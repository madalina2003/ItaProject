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
import androidx.compose.ui.platform.LocalContext
import com.tus.tusparking.ui.theme.TUSParkingTheme



class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
       // add the map
//       val mapFragment = MapFragment.newInstance(
//     GoogleMapOptions()
//         .mapId(resources.getString(R.string.map_id))
// )


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
fun TUSParkingApp() {
    val navController = rememberNavController()

    TUSParkingTheme {
        Surface(
            modifier = androidx.compose.ui.Modifier.fillMaxSize(),
            color = MaterialTheme.colorScheme.background
        ) {

            NavHost(navController = navController, startDestination = "HomeScreen") {

                    composable("HomeScreen") {
                        HomeScreen(navController)
                    }
                    composable("SignScreen") {
                        SignScreen(navController)
                    }
                    composable("Maps") {
                        MapScreen(navController)
                    }
                    composable("Search") {
                        SearchScreen(navController)
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
            LaunchedEffect(key1 = true ){
                if(!postNotificationPermission.status.isGranted){
                    postNotificationPermission.launchPermissionRequest()
                }
            }
            Column {
                Button(
                    onClick = {
                        notificationService.showBasicNotification()
                    }
                ) {
                    Text(text = "Show basic notifications")
                }
                Spacer(modifier = Modifier.height(10.dp))

                Button(
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