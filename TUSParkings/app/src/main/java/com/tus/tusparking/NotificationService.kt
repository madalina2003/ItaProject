package com.tus.tusparking

import android.app.NotificationManager
import android.content.Context
import android.graphics.BitmapFactory
import androidx.annotation.DrawableRes
import androidx.core.app.NotificationCompat
import kotlin.random.Random

class NotificationService (
    private val context: Context
    ){
        private val notificationManager=context.getSystemService(NotificationManager::class.java)
        fun showBasicNotification(){
            val notification= NotificationCompat.Builder(context,"tus_parking")
                .setContentTitle("Parking reminder")
                .setContentText("Time repark your car")
                .setSmallIcon(R.drawable.icon)
                .setPriority(NotificationManager.IMPORTANCE_HIGH)
                .setAutoCancel(true)
                .build()

            notificationManager.notify(
                Random.nextInt(),
                notification
            )
        }

        fun showExpandableNotification(){
            val notification=NotificationCompat.Builder(context,"tus_parking")
                .setContentTitle("Parking Reminder")
                .setContentText("Time to repark your car")
                .setSmallIcon(R.drawable.icon)
                .setPriority(NotificationManager.IMPORTANCE_HIGH)
                .setAutoCancel(true)
                .setStyle(
                    NotificationCompat
                        .BigPictureStyle()
                        .bigPicture(
                            context.bitmapFromResource(
                                R.drawable.icon
                            )
                        )
                )
                .build()
            notificationManager.notify(Random.nextInt(),notification)
        }

        private fun Context.bitmapFromResource(
            @DrawableRes resId:Int
        )= BitmapFactory.decodeResource(
            resources,
            resId
        )
    }