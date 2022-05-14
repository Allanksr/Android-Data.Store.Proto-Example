package allanksr.com.datastore_example.common

import android.content.Context
import android.widget.Toast
import android.widget.Toast.LENGTH_SHORT

fun Context.toast(text: String, duration: Int = LENGTH_SHORT) {
    Toast.makeText(
        applicationContext,
        text,
        duration
    ).show()
}