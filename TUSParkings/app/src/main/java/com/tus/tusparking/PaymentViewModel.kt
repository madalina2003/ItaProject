package com.tus.tusparking

import androidx.lifecycle.ViewModel
import androidx.lifecycle.MutableLiveData
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore

class PaymentViewModel : ViewModel() {
    private val auth: FirebaseAuth = FirebaseAuth.getInstance()
    private val db: FirebaseFirestore = FirebaseFirestore.getInstance()

    val errorMessage = MutableLiveData<String>()
    val paymentStatus = MutableLiveData<Boolean>()

    fun processPayment(cardNumber: String, expiryDate: String, cvv: String) {

        println("Card Number: $cardNumber, Expiry Date: $expiryDate, CVV: $cvv")

        // Save payment details to Firestore
        savePaymentToFirestore(cardNumber, expiryDate, cvv)
    }

    private fun savePaymentToFirestore(cardNumber: String, expiryDate: String, cvv: String) {
        val userId = auth.currentUser?.uid
        if (userId != null) {
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
        } else {
            errorMessage.postValue("User not authenticated")
            paymentStatus.postValue(false) // Payment failed
        }
    }



    override fun onCleared() {

        super.onCleared()
    }
}
