package com.tus.tusparking

import androidx.lifecycle.ViewModel
import androidx.lifecycle.MutableLiveData
import com.google.firebase.auth.FirebaseAuth


class HomeScreenViewModel:ViewModel() {
    private val auth: FirebaseAuth = FirebaseAuth.getInstance()

    val errorMessage = MutableLiveData<String>()
    val userId = MutableLiveData<String?>()

    fun registerUser(email: String, password: String) {
        // Check if the email or password field is empty
        if (email.isEmpty() || password.isEmpty()) {
            errorMessage.postValue("email or password cannot be empty")
            return
        }
        auth.createUserWithEmailAndPassword(email, password)
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    // User is registered
                    val currentUser = auth.currentUser
                    if (currentUser != null) {
                        // Post the user ID to a LiveData
                        userId.postValue(currentUser.uid)

                    } else {
                        // Handle the case where currentUser is null
                        errorMessage.postValue("An error has occurred, Please Try again later")
                    }
                } else {
                    // Handle failure
                    errorMessage.postValue(task.exception?.message)
                }
            }
    }

    //Login User Function
    fun loginUser(email: String, password: String) {
        // Check if the email or password field is empty
        if (email.isEmpty() || password.isEmpty()) {
            errorMessage.postValue("email or password cannot be empty")
            return
        }

        auth.signInWithEmailAndPassword(email, password)
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    // User is logged in
                    val currentUser = auth.currentUser
                    if (currentUser != null) {
                        // Post the user ID to a LiveData
                        userId.postValue(currentUser.uid)
                    } else {

                        errorMessage.postValue("An error has occurred, Please Try again later")
                    }
                } else {
                    // Handle failure
                    errorMessage.postValue(task.exception?.message)
                }
            }
    }

    //Log out function
    fun logoutUser() {
        auth.signOut()
        userId.postValue(null)
    }
}
