package coloring.com.camera_coloring_book.Util

import android.content.Context
import android.content.SharedPreferences

private fun getPref(context: Context): SharedPreferences {
    val pref = context.getSharedPreferences("pref", Context.MODE_PRIVATE)
    return pref
}

fun saveInfo(context: Context, key: String, value: String){
    val editor = getPref(context).edit()
    editor.putString(key,value)
    editor.apply()
}

fun getInfo(context: Context,key: String) : String{
    return getPref(context).getString(key,"")
}


private fun getKey(isAccess: Boolean): String = if (isAccess) "Access" else "Refresh"