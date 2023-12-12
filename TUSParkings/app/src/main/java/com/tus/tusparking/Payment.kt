package com.tus.tusparking

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment

import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import androidx.lifecycle.viewmodel.compose.viewModel
import com.tus.tusparking.ui.theme.TUSParkingTheme


@Composable
fun PaymentScreen(navController: NavController,viewModel: PaymentViewModel = viewModel()) {
    var cardNumber by remember { mutableStateOf("") }
    var expiryDate by remember { mutableStateOf("") }
    var cvv by remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(
            painter = painterResource(id = R.drawable.card),
            contentDescription = "Credit Card Image",
            modifier = Modifier
                .size(200.dp)
                .padding(bottom = 16.dp)
        )

        Text("Enter Card Details", fontSize = 24.sp, fontWeight = FontWeight.Bold)

        Spacer(modifier = Modifier.height(16.dp))

        CardDetailsTextField("Card Number", cardNumber) { cardNumber = it }
        CardDetailsTextField("Expiry Date", expiryDate) { expiryDate = it }
        CardDetailsTextField("CVV", cvv) { cvv = it }

        Spacer(modifier = Modifier.height(32.dp))

        Button(
            onClick = {
               // viewModel.processPayment(cardNumber, expiryDate, cvv)
                      navController.navigate(Screen.PaymentSuccessScreen.route)
            },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Process Payment")
        }
    }
}

@Composable
fun CardDetailsTextField(label: String, value: String, onValueChange: (String) -> Unit) {
    OutlinedTextField(
        value = value,
        onValueChange = { onValueChange(it) },
        label = { Text(label) },
        keyboardOptions = KeyboardOptions(
            keyboardType = KeyboardType.Number,
            imeAction = ImeAction.Done
        ),
        visualTransformation = if (label == "CVV") PasswordVisualTransformation() else VisualTransformation.None,
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp)
    )
}


@Preview(showBackground = true)
@Composable
fun Payment() {
    TUSParkingTheme {
        val navController = rememberNavController()
        PaymentScreen(navController)
      //  SignScreen(navController)

    }
}
