package com.madhavth.daggermvvmapp.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import com.madhavth.daggermvvmapp.R
import com.madhavth.daggermvvmapp.dagger.main.DaggerMainComponent
import javax.inject.Inject

class MainActivity : AppCompatActivity() {

//    @Inject
//    lateinit var factory: ViewModelProvider.Factory

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val mainComponent = DaggerMainComponent.create()
        mainComponent.inject(this)


    }
}