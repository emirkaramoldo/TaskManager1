package com.example.taskmanager1.data.local

import android.content.Context
import android.content.Context.MODE_PRIVATE

class Pref(context: Context) {

    private val pref = context.getSharedPreferences(PREF_NAME,MODE_PRIVATE)

    fun isShowed(): Boolean{
        return pref.getBoolean(SHOWED_KEY,false)
    }

    fun onBoardingShow(){
        pref.edit().putBoolean(SHOWED_KEY,true).apply()
    }

    fun saveName(name: String){
        pref.edit().putString(NAME_KEY,name).apply()
    }

    fun getName(): String?{
        return pref.getString(NAME_KEY,"")
    }

    fun saveAvatar(url: String){
        pref.edit().putString(AVATAR_KEY,url).apply()
    }

    fun getAvatar(): String?{
        return pref.getString(AVATAR_KEY,"")
    }

    companion object{
        const val PREF_NAME = "task.manager.pref"
        const val SHOWED_KEY = "showed.key"
        const val NAME_KEY = "name.key"
        const val AVATAR_KEY = "avatar.key"
    }
}