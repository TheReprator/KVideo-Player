package dev.reprator.video.demo

import android.app.Application
import android.os.Build
import android.os.StrictMode
import android.os.StrictMode.ThreadPolicy
import android.os.StrictMode.VmPolicy
import kotlin.apply

class VideoApp : Application() {


    override fun onCreate() {
        super.onCreate()
        setupStrictMode()
    }

    private fun setupStrictMode() {
        StrictMode.setThreadPolicy(
            ThreadPolicy
                .Builder()
                .detectAll()
                .penaltyLog()
                .build(),
        )
        StrictMode.setVmPolicy(
            VmPolicy
                .Builder()
                .detectLeakedSqlLiteObjects()
                .detectActivityLeaks()
                .detectLeakedClosableObjects()
                .detectLeakedRegistrationObjects()
                .detectFileUriExposure()
                .apply {
                    if (Build.VERSION.SDK_INT >= 26) {
                        detectContentUriWithoutPermission()
                    }
                    if (Build.VERSION.SDK_INT >= 29) {
                        detectCredentialProtectedWhileLocked()
                    }
                    if (Build.VERSION.SDK_INT >= 31) {
                        detectIncorrectContextUse()
                        detectUnsafeIntentLaunch()
                    }
                }.penaltyLog()
                .build(),
        )
    }
}
