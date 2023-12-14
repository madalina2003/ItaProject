package com.tus.tusparking

import android.content.ContentValues.TAG
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.MutableLiveData
import com.google.android.gms.maps.model.LatLng
import com.google.firebase.Firebase
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.firestore


class HomeScreenViewModel:ViewModel() {

    //reference  : https://firebase.google.com/docs/auth/android/start
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


    private val _markers = MutableLiveData<List<LatLng>>()
    val markers: LiveData<List<LatLng>> = _markers

    fun fetchMarkers() {
        val db = Firebase.firestore
        val collectionRef = db.collection("marker")

        collectionRef.get().addOnSuccessListener { result ->
            val markerList = mutableListOf<LatLng>()

            for (document in result) {
                val location = document.getGeoPoint("location")
                if (location != null) {
                    markerList.add(LatLng(location.latitude, location.longitude))
                }
            }

            _markers.value = markerList
            Log.d(TAG, "Markers fetched: $markerList")
        }.addOnFailureListener { exception ->
            Log.e(TAG, "Fetch markers failed", exception)
        }
    }


                //attempt of changing marker colour based on status

//    data class MarkerData(val position: LatLng, val status: Boolean)
//
//    private val _markers = MutableLiveData<List<MarkerData>>()
//    val markers: LiveData<List<MarkerData>> = _markers
//
//    fun fetchMarkers() {
//        val db = Firebase.firestore
//        val collectionRef = db.collection("marker")
//
//        collectionRef.get().addOnSuccessListener { result ->
//            val markerList = mutableListOf<MarkerData>()
//
//            for (document in result) {
//                val location = document.getGeoPoint("location")
//                val status = document.getBoolean("status") ?: false
//
//                if (location != null) {
//                    markerList.add(MarkerData(LatLng(location.latitude, location.longitude), status))
//                }
//            }
//
//            _markers.value = markerList
//            Log.d(TAG, "Markers fetched: $markerList")
//        }.addOnFailureListener { exception ->
//            Log.e(TAG, "Fetch markers failed", exception)
//        }
//    }






    //Log out function
    fun logoutUser() {
        auth.signOut()
        userId.postValue(null)
    }
}
