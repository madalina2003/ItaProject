package com.tus.tusparking
import android.annotation.SuppressLint
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowLeft
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Color.Companion.Transparent
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import androidx.lifecycle.viewmodel.compose.viewModel
import com.tus.tusparking.ui.theme.TUSParkingTheme



@SuppressLint("UnrememberedMutableState")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SignScreen(navController: NavController , viewModel: HomeScreenViewModel = viewModel()){


    Scaffold(
        topBar = {SignTopAppBar(navController)}

    ) {paddingValues ->
        Column(modifier = Modifier
            .padding(paddingValues)
            .verticalScroll(rememberScrollState()),
            horizontalAlignment = Alignment.CenterHorizontally) {
            Column() {
                Spacer(modifier = Modifier.height(50.dp))
                SignTopText()
                SignMainContent(navController)

            }

        }
    }

}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SignTopAppBar(navController: NavController){
    TopAppBar(
        title = {
            Row(verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier
                    .background(Color.Black)
                        .fillMaxHeight()
                    .padding(horizontal = 70.dp, vertical = 24.dp))
            {
                IconButton(onClick = { navController.popBackStack() }) {

                    Icon(imageVector = Icons.Filled.KeyboardArrowLeft,modifier = Modifier.size(25.dp), contentDescription = stringResource(
                        R.string.sign_up_screen_back_button)
                    )
                }
                Spacer(modifier = Modifier.width(25.dp))

                Text(
                    stringResource(R.string.home_screen_text),
                    fontWeight = FontWeight.Bold,
                    color = Color.White
                )


                Image(
                    painter = painterResource(R.drawable.tus),
                    contentDescription = stringResource(R.string.home_screen_image_content),
                    contentScale = ContentScale.Fit,
                    modifier = Modifier
                        .size(50.dp)
                )


            }
        },
        actions = {
        }

    )




}

@Composable
fun SignTopText(){

    Spacer(modifier = Modifier.height(20.dp))
    Row(modifier = Modifier.fillMaxWidth()) {
        Text(text = stringResource(R.string.sign_in_screen_text),
            textAlign = TextAlign.Center,
            modifier = Modifier.fillMaxWidth(),

            fontWeight=FontWeight.Bold,
            fontSize = 20.sp,)
    }

}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SignMainContent(navController:NavController, viewModel: HomeScreenViewModel = viewModel()){
    var email by remember{mutableStateOf("")}
    var password by remember{mutableStateOf("")}
    val errorMessage by viewModel.errorMessage.observeAsState()

    val userId = viewModel.userId.observeAsState()


    Spacer(modifier = Modifier.height(20.dp))
    Column(modifier = Modifier
        .padding(
            start= dimensionResource(R.dimen.padding_input_fields_start),
            end = dimensionResource(R.dimen.padding_input_fields_end),
            top = dimensionResource(R.dimen.padding_input_fields_top),
            bottom = dimensionResource(R.dimen.padding_input_fields_bottom)
        )){
        Spacer(modifier = Modifier.height(20.dp))
        if (errorMessage != null) {
            AlertDialog(
                onDismissRequest = { viewModel.errorMessage.value = null },
                title = { Text("Error") },
                text = { Text(text = errorMessage!!) },
                confirmButton = {
                    TextButton(onClick = { viewModel.errorMessage.value = null }) {
                        Text(text = "OK")
                    }
                }
            )
        }

        Spacer(modifier = Modifier.height(20.dp))
        OutlinedTextField(
            value = email,
            singleLine = true,
            shape = MaterialTheme.shapes.medium,
            modifier = Modifier.fillMaxWidth(),
            onValueChange = {email=it},
            colors = TextFieldDefaults.outlinedTextFieldColors(
                focusedBorderColor = MaterialTheme.colorScheme.surface,
                unfocusedBorderColor = MaterialTheme.colorScheme.surface,
                disabledBorderColor = MaterialTheme.colorScheme.surface,
                cursorColor = Color.Gray,
            ),
            label = { Text(
                stringResource(R.string.sign_up_screen_email),
                fontWeight=FontWeight.Normal,
                fontSize = 20.sp,) },
            isError = false,
            keyboardOptions = KeyboardOptions.Default,
            keyboardActions = KeyboardActions.Default,
        )
        Spacer(modifier = Modifier.height(20.dp))

        OutlinedTextField(
            value = password,
            singleLine = true,
            shape = MaterialTheme.shapes.medium,
            modifier = Modifier.fillMaxWidth(),
            onValueChange = {password = it },
            colors = TextFieldDefaults.outlinedTextFieldColors(
                focusedBorderColor = MaterialTheme.colorScheme.surface,
                unfocusedBorderColor = MaterialTheme.colorScheme.surface,
                disabledBorderColor = MaterialTheme.colorScheme.surface,
                cursorColor = Color.Gray,
                //focusedIndicatorColor = Color.Gray
            ),
            label = { Text(stringResource(R.string.sign_up_screen_password) ,fontSize = 20.sp,) },
            isError = false,
            keyboardOptions = KeyboardOptions.Default,
            keyboardActions = KeyboardActions.Default,
            visualTransformation = PasswordVisualTransformation()
        )
        Spacer(modifier = Modifier.height(15.dp))

    }
    //Button
    Column(modifier = Modifier
        .padding(
            start = dimensionResource(R.dimen.padding_sign_up_button_start),
            end = dimensionResource(R.dimen.padding_sign_up_button_end)
        )){
        Button(
            onClick = {
                viewModel.registerUser(email.lowercase(), password)
                if (userId.value != null) {
                    navController.navigate("maps/${userId.value}")

                }},
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
        Spacer(modifier = Modifier.height(10.dp))

    }

    Button(
        onClick = { navController.navigate(Screen.HomeScreen.route) },
        modifier = Modifier.padding(10.dp),
        colors = ButtonDefaults.buttonColors(containerColor = Transparent, contentColor = Color.Black),
    ) {
        Text(
            text = " Login?",
            modifier = Modifier
                .padding(150.dp,5.dp)
        )
    }

    Spacer(modifier = Modifier.height(100.dp))

    //logout footer
//    Box(
//        modifier = Modifier
//            .fillMaxWidth()
//            .padding(12.dp)
//            .background(Color.Black)
//    ) {
//        Row(
//            modifier = Modifier.fillMaxWidth(),
//            horizontalArrangement = Arrangement.End
//        ) {
//            Button(
//                onClick = {
//                    // Add your logout logic here
//                    // For example: viewModel.logout()
//                    navController.popBackStack() // Replace this with your actual navigation logic
//                }
//            ) {
//                Text(text = "Logout")
//            }
//        }
//    }


}

@Preview(showBackground = true)
@Composable
fun SignScreenPreview() {
    TUSParkingTheme {
        val navController = rememberNavController()
        SignMainContent(navController)
        SignScreen(navController)

    }
}

@Preview
@Composable
fun SignScreenDarkPreview() {
    TUSParkingTheme(darkTheme = true) {
        val navController = rememberNavController()
        SignScreen(navController)

    }
}
