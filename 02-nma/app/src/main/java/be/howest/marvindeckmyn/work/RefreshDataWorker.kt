package be.howest.marvindeckmyn.work

import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.app.PendingIntent.FLAG_UPDATE_CURRENT
import android.content.Context
import android.content.Intent
import android.graphics.BitmapFactory
import android.graphics.Color
import android.os.Build
import android.util.Log
import androidx.annotation.RequiresApi
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters
import be.howest.marvindeckmyn.MainActivity
import be.howest.marvindeckmyn.R
import retrofit2.HttpException

class RefreshDataWorker(appContext: Context, params: WorkerParameters) :
        CoroutineWorker(appContext, params) {

    private lateinit var notificationManager : NotificationManager
    private lateinit var notificationChannel : NotificationChannel
    private lateinit var builder : Notification.Builder
    private val channelId = "be.howest.marvindeckmyn.MainActivity"
    private val description = "There are new beats uploaded this week. Go check them out!"

    companion object {
        const val WORK_NAME = "be.howest.marvindeckmyn.work.RefreshDataWorker"
    }

    @RequiresApi(Build.VERSION_CODES.O)
    override suspend fun doWork(): Result {
        try {
            notifyUsers()
        } catch (e: HttpException) {
            return Result.retry()
        }

        return Result.success()
    }

    @RequiresApi(Build.VERSION_CODES.O)
    private fun notifyUsers() {
        val intent = Intent(applicationContext, MainActivity::class.java)
        val pendingIntent = PendingIntent.getActivity(applicationContext, 0, intent, FLAG_UPDATE_CURRENT)

        notificationChannel = NotificationChannel(channelId,description,NotificationManager.IMPORTANCE_HIGH)
        notificationChannel.enableLights(true)
        notificationChannel.lightColor = Color.MAGENTA
        notificationChannel.enableVibration(false)
        notificationManager.createNotificationChannel(notificationChannel)

        builder = Notification.Builder(applicationContext, channelId)
            .setContentTitle("Universal Beats")
            .setContentText("There are new beats uploaded this week. Go check them out!")
            .setSmallIcon(R.mipmap.ic_ub_logo_round)
            .setLargeIcon(BitmapFactory.decodeResource(applicationContext.resources, R.mipmap.ic_ub_logo))
            .setContentIntent(pendingIntent)

        notificationManager.notify(1, builder.build())
    }

}