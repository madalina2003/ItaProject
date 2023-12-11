package com.tus.tusparking

import android.app.Application
import com.google.firebase.FirebaseApp

class TusParking: Application() {
    override fun onCreate(){
        super.onCreate()
        FirebaseApp.initializeApp(this)
    }
}