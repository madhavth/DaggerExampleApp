package com.madhavth.daggermvvmapp.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.madhavth.daggermvvmapp.R
import javax.inject.Inject

class MainActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }
}


/*

ext{
        version_android_gradle_plugin = "3.5.2"
        version_kotlin = "1.3.61"
        version_core = "1.1.0"
        version_constraint_layout = "1.1.3"
        version_glide = "4.8.0"
        version_kotlin_coroutines = "1.1.0"
        version_lifecycle_extensions = "2.1.0"
        version_moshi = "1.8.0"
        version_navigation = "1.0.0"
        version_retrofit = "2.5.0"
        version_retrofit_coroutines_adapter = "0.9.2"
        version_recyclerview = "1.0.0"
        room_version = "2.2.3"
        location_version = "17.0.0"
        material_version = '1.2.0-alpha02'
    }

    // add as classpath
            classpath "android.arch.navigation:navigation-safe-args-gradle-plugin:$version_navigation"
    */