package com.madhavth.daggermvvmapp.utils

import android.widget.Button
import androidx.databinding.BindingAdapter
import timber.log.Timber

@BindingAdapter("showText")
fun showText(v: Button, check: Boolean)
{
    Timber.d("check is $check")

    if(check)
    {
        v.text = "Show Retrofit List"
    }

    else
    {
        v.text = "Show Room List"
    }
}