package com.tus.tusparking

import androidx.lifecycle.ViewModel
import androidx.lifecycle.MutableLiveData
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore

class PaymentViewModel : ViewModel() {

    //reference : https://medium.com/@deepak140596/firebase-firestore-using-view-models-and-livedata-f9a012233917
    private val auth: FirebaseAuth = FirebaseAuth.getInstance()
    private val db: FirebaseFirestore = FirebaseFirestore.getInstance()

    val errorMessage = MutableLiveData<String>()
    val paymentStatus = MutableLiveData<Boolean>()

    fun processPayment(userId: String,cardNumber: String, expiryDate: String, cvv: String) {

        println("Card Number: $cardNumber, Expiry Date: $expiryDate, CVV: $cvv")

        // Save payment details to Firestore
        savePaymentToFirestore(userId,cardNumber, expiryDate, cvv)
    }

   fun savePaymentToFirestore(userId: String, cardNumber: String, expiryDate: String, cvv: String) {
        val paymentData = hashMapOf(
            "cardNumber" to cardNumber,
            "expiryDate" to expiryDate,
            "cvv" to cvv
        )


            val paymentsCollection = db.collection("users").document(userId).collection("payments")

            paymentsCollection.add(paymentData)
                .addOnSuccessListener {
                    // Handle success
                    paymentStatus.postValue(true)
                   // navController.navigate(Screen.PaymentSuccessScreen.route)// Successful payment
                }
                .addOnFailureListener { e ->
                    // Handle failure
                    errorMessage.postValue(e.message)
                    paymentStatus.postValue(false) // Payment failed
                }
        }
    }


