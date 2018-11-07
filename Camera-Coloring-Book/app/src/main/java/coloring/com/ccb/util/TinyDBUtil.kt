package coloring.com.ccb.util

import android.content.Context
import coloring.com.ccb.model.ARGB

object TinyDBUtil {
    fun loadSharedPreferencesData(context: Context): ArrayList<ARGB> {
        val db = TinyDB(context)
        return db.getListObject("color", ARGB::class.java) as ArrayList<ARGB>
    }

    fun saveSharedPreferenceData(context: Context, list: ArrayList<ARGB>) {
        val db = TinyDB(context)
        db.putListObject("color", list as ArrayList<Any>)
    }
}