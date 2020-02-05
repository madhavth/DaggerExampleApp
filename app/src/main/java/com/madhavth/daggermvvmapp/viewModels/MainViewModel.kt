package com.madhavth.daggermvvmapp.viewModels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.madhavth.daggermvvmapp.data.repository.MyRepository
import javax.inject.Inject

class MainViewModel @Inject constructor(private val repo: MyRepository): ViewModel()
{

    private val simpleData = MutableLiveData<String>()

    fun getData()
    {
        simpleData.value = repo.getData()
    }

}