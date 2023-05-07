package com.example.professionworker

import android.app.Application
import android.content.Context
import android.content.ContextWrapper
import androidx.appcompat.app.AppCompatDelegate
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.createDataStore
import com.example.professionworker.data.repo.PrefsHelper
import com.example.professionworker.util.Constants
import com.example.professionworker.util.ContextUtils
import com.example.professionworker.util.SessionManager

import dagger.hilt.android.HiltAndroidApp
import java.util.*
import javax.inject.Inject

@HiltAndroidApp
class MyApp : Application() {

    @Inject
    lateinit var sessionManager: SessionManager

    companion object CompanionObject {

        lateinit var dataStore: DataStore<Preferences>
    }

    override fun onCreate() {
        super.onCreate()
      //  AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
        dataStore = createDataStore(name = "settings")
        PrefsHelper.init(applicationContext)

    }

    override fun attachBaseContext(newBase: Context) {
        val locale = Locale(Constants.AR)
        val localeUpdatedContext: ContextWrapper = ContextUtils.updateLocale(newBase, locale)
        super.attachBaseContext(localeUpdatedContext)
    }

}