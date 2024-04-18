package com.horizon.professionworker

import android.app.Application
import android.content.Context
import android.content.ContextWrapper
import androidx.appcompat.app.AppCompatDelegate
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.createDataStore
import com.horizon.professionworker.data.repo.PrefsHelper
import com.horizon.professionworker
.util.Constants
import com.horizon.professionworker.util.ContextUtils

import dagger.hilt.android.HiltAndroidApp
import java.util.*
import javax.inject.Inject

@HiltAndroidApp
class MyApp : Application() {


    companion object CompanionObject {

        lateinit var dataStore: DataStore<Preferences>
    }

    override fun onCreate() {
        super.onCreate()
      AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
        dataStore = createDataStore(name = "settings")
        PrefsHelper.init(applicationContext)

    }



}