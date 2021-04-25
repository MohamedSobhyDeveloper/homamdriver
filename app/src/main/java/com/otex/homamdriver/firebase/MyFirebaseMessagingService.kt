package com.otex.homamdriver.firebase

import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.media.RingtoneManager
import android.util.Log
import androidx.core.app.NotificationCompat
import androidx.core.content.ContextCompat
import com.google.firebase.messaging.FirebaseMessagingService
import com.google.firebase.messaging.RemoteMessage
import com.otex.homamdriver.R
import com.otex.homamdriver.utlitites.DataEnum
import com.otex.homamdriver.view.home.homerestaurant.HomeActivity
import com.otex.homamuser.utlitites.PrefsUtil


class MyFirebaseMessagingService : FirebaseMessagingService() {
    val CHANNEL_ID = "com.otex.homamdriver"

    val CHANNEL_NAME = "com.otex.homamdriver"
    override fun onMessageReceived(remoteMessage: RemoteMessage) {

            if (remoteMessage.notification != null) {

                showGenericNotification(
                    remoteMessage,
                    remoteMessage.notification?.title,
                    remoteMessage.notification?.body
                )
            } else if (remoteMessage.data != null) {
                showGenericNotification(
                    remoteMessage,
                    remoteMessage.data?.get("body"),
                    remoteMessage.data?.get("body")
                )

            }


    }

    override fun onNewToken(token: String) {
        Log.d("token", "Refreshed token: $token")
        PrefsUtil.with(this).add(DataEnum.firebaseToken.name, token).apply()

    }

    private fun showGenericNotification(
        remoteMessage: RemoteMessage,
        title: String?,
        body: String?
    ) {


        val notificationManager =
            getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        val defaultSoundUri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION)



        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
            val defaultChannel =
                NotificationChannel(CHANNEL_ID, CHANNEL_NAME, NotificationManager.IMPORTANCE_HIGH)
            notificationManager.createNotificationChannel(defaultChannel)
        }


        var intent = Intent(this, HomeActivity::class.java)
        val data = remoteMessage.getData()

        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)

        val pendingIntent = PendingIntent.getActivity(
            this, 0, intent,
            PendingIntent.FLAG_ONE_SHOT
        )


        val notificationBuilder = NotificationCompat.Builder(this, CHANNEL_ID)
            .setSmallIcon(R.mipmap.ic_launcher)
            .setColor(ContextCompat.getColor(this, R.color.purple_200))
            .setContentTitle(title)
            .setContentText(body)
            .setWhen(System.currentTimeMillis())
            .setSound(defaultSoundUri)
            .setDefaults(Notification.DEFAULT_SOUND or Notification.DEFAULT_VIBRATE)
            .setLights(ContextCompat.getColor(this, R.color.purple_200), 5000, 5000)
            .setAutoCancel(true)
            .setPriority(NotificationCompat.PRIORITY_MAX)
            .setContentIntent(pendingIntent)

        notificationManager.notify("myapp", 0, notificationBuilder.build())

    }

}