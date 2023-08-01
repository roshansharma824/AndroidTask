package com.example.androidtask

import android.accessibilityservice.AccessibilityService
import android.accessibilityservice.AccessibilityServiceInfo
import android.content.pm.ApplicationInfo
import android.content.pm.PackageManager
import android.os.Bundle
import android.util.Log
import android.view.accessibility.AccessibilityEvent
import android.view.accessibility.AccessibilityNodeInfo
import android.widget.Toast
import androidx.core.view.accessibility.AccessibilityNodeInfoCompat


class MyAccessibilityService : AccessibilityService() {

    override fun onInterrupt() {
        Toast.makeText(applicationContext,"Something went wrong",Toast.LENGTH_LONG).show()
        Log.e("MyAccessibilityService","Something went wrong")
    }

    override fun onAccessibilityEvent(event: AccessibilityEvent?) {

        val packageName = event?.packageName.toString()

        val packageManager = this.packageManager

//        try {
//
//            val applicationInfo: ApplicationInfo = packageManager.getApplicationInfo(packageName,0)
//            val applicationLabel = packageManager.getApplicationLabel(applicationInfo)
//
//            Toast.makeText(applicationContext,"app name is: $applicationLabel",Toast.LENGTH_LONG).show()
//
//
//        }catch (e: PackageManager.NameNotFoundException){
//            e.printStackTrace()
//        }
        if (packageName == "com.whatsapp"){
            Toast.makeText(applicationContext,"WhatsApp Launched",Toast.LENGTH_SHORT).show()
        }


    }

    override fun onServiceConnected() {
        super.onServiceConnected()

        Log.d("MyAccessibilityService","onServiceConnected")
        val info = AccessibilityServiceInfo()
        info.eventTypes = AccessibilityEvent.TYPE_NOTIFICATION_STATE_CHANGED
        info.eventTypes = AccessibilityEvent.TYPES_ALL_MASK
        info.feedbackType = AccessibilityServiceInfo.FEEDBACK_ALL_MASK
        info.notificationTimeout = 100
        info.packageNames = Array<String>(1) { "com.whatsapp" }
        serviceInfo = info





    }

}
