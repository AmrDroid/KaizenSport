package com.aelsayed.kaizen.util


import android.text.format.DateFormat


object DateConverter {

    fun untilEvent(timeStamp: String): String {

        return if (timeStamp.isNotEmpty()) {
            DateFormat.format("HH:mm:ss", timeStamp.toLong()).toString()
        } else ""

    }


}