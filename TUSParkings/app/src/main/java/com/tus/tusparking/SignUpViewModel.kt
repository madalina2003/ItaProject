package com.tus.tusparking.com.tus.tusparking

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.tus.tusparking.Student

class SignUpViewModel : ViewModel() {
    private val auth: FirebaseAuth = FirebaseAuth.getInstance()


    private val database: FirebaseDatabase =
        FirebaseDatabase.getInstance("https://tusParking-default-rtdb.europe-west1.firebasedatabase.app/")

    val error = MutableLiveData<String>()
    val userId = MutableLiveData<String>()

    fun register(email: String, password: String) {
        val studentDataRef = database.getReference("StudentData")
        val query = studentDataRef.orderByChild("Email").equalTo(email)

        query.addListenerForSingleValueEvent(object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                if (dataSnapshot.exists()) {
                    //if exist allow registering
                    val studentRecordData =
                        dataSnapshot.children.iterator().next().getValue(Student::class.java)
                    if (studentRecordData != null) {
                        createUser(email, password, studentRecordData)
                    } else {
                        //if the data is null
                        error.postValue("Try again later!")
                    }
                } else {
                    // The email does not exist
                    error.postValue("The email does not exist.")
                }
            }

            override fun onCancelled(databaseError: DatabaseError) {
                // Handle error
                error.postValue(databaseError.message)
            }
        })
    }

    //Create User
    private fun createUser(email: String, password: String, studentRecord: Student) {

        if (email.isEmpty() || password.isEmpty()) {
            error.postValue("Email or password cannot be empty")
            return
        }

        auth.createUserWithEmailAndPassword(email, password)
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    // update the data after registration
                    val usersData = database.getReference("Data")
                    val currentUser = auth.currentUser
                    if (currentUser != null) {
                        val record = com.tus.tusparking.User(email, studentRecord.password)
                        usersData.child(currentUser.uid).setValue(record)
                        userId.postValue(currentUser.uid)
                    } else {
                        //if current user is empty
                        error.postValue("Please try again later")
                    }

                } else {
                    error.postValue(task.exception?.message)
                }
            }
    }
}
