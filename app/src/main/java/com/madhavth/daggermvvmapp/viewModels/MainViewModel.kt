package com.madhavth.daggermvvmapp.viewModels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.madhavth.daggermvvmapp.data.repository.MyRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import okhttp3.Dispatcher
import javax.inject.Inject

class MainViewModel @Inject constructor(private val repo: MyRepository): ViewModel()
{

    private var job = Job()
    private val coroutineScope = CoroutineScope(Dispatchers.IO + job)

    private var _simpleData = MutableLiveData<String>()
        val simpleData : LiveData<String>
            get() = _simpleData

    fun getData()
    {
        _simpleData.value =  repo.getData()
    }


    fun getTodoLists()
    {
        coroutineScope.launch {

        }
    }



    override fun onCleared() {
        super.onCleared()
        job.cancel()
    }
}