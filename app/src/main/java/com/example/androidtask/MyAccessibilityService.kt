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

        try {

            val applicationInfo: ApplicationInfo = packageManager.getApplicationInfo(packageName,0)
            val applicationLabel = packageManager.getApplicationLabel(applicationInfo)

            Toast.makeText(applicationContext,"app name is: $applicationLabel",Toast.LENGTH_LONG).show()


        }catch (e: PackageManager.NameNotFoundException){
            e.printStackTrace()
        }

        Toast.makeText(applicationContext,"$packageName",Toast.LENGTH_LONG).show()


        if (event?.eventType === AccessibilityEvent.TYPE_NOTIFICATION_STATE_CHANGED) {
            if (event.packageName.toString() == "com.whatsapp") {
                Log.d("MyAccessibilityService","whatsapp open")
                Toast.makeText(applicationContext,"whatsapp open",Toast.LENGTH_LONG).show()
                val message = StringBuilder()
                if (!event.text.isEmpty()) {
                    for (subText in event.text) {
                        message.append(subText)
                    }
                    if (message.toString().contains("Message from")) {
//                        name = message.toString().substring(13)
                    }
                }
            }
        }






        if (event?.eventType === AccessibilityEvent.TYPE_WINDOW_CONTENT_CHANGED) {
            Log.d("MyAccessibilityService","whatsapp open")
            if (event.source?.packageName == "com.whatsapp") {
                Log.d("MyAccessibilityService","whatsapp open")
                Toast.makeText(applicationContext,"whatsapp open",Toast.LENGTH_LONG).show()
                val currentNode = rootInActiveWindow
                if (currentNode != null && currentNode.className == "android.widget.FrameLayout" && currentNode.getChild(
                        2
                    ) != null && currentNode.getChild(2).className == "android.widget.TextView" && currentNode.getChild(
                        2
                    ).contentDescription == "Search"
                ) {
                    Log.d("MyAccessibilityService","whatsapp open")
                    Toast.makeText(applicationContext,"whatsapp open",Toast.LENGTH_LONG).show()
                    currentNode.getChild(2).performAction(AccessibilityNodeInfo.ACTION_CLICK)
                }
            }
        }


//        val textBox: AccessibilityNodeInfo = getNode(rootNode, chatBoxRefId)
//        val arguments = Bundle()
//        if (convIndex === convs.length - 1) {
//            arguments.putString(
//                AccessibilityNodeInfoCompat.ACTION_ARGUMENT_SET_TEXT_CHARSEQUENCE,
//                "<3"
//            )
//        } else {
//            arguments.putString(
//                AccessibilityNodeInfoCompat.ACTION_ARGUMENT_SET_TEXT_CHARSEQUENCE,
//                convs.get(convIndex % (convs.length - 1))
//            )
//            convIndex++
//        }
//        textBox.performAction(AccessibilityNodeInfoCompat.ACTION_SET_TEXT, arguments)
//        val sendButton: AccessibilityNodeInfo = getNode(rootNode, sendButtonRefId)
//        sendButton.performAction(AccessibilityNodeInfo.ACTION_CLICK)


    }

    override fun onServiceConnected() {
        super.onServiceConnected()

        println("onServiceConnected")
        val info = AccessibilityServiceInfo()
        info.eventTypes = AccessibilityEvent.TYPE_NOTIFICATION_STATE_CHANGED
        info.eventTypes = AccessibilityEvent.TYPES_ALL_MASK
        info.feedbackType = AccessibilityServiceInfo.FEEDBACK_ALL_MASK
        info.notificationTimeout = 100
        info.packageNames = Array<String>(1) { "com.whatsapp" }
        serviceInfo = info





    }

}
