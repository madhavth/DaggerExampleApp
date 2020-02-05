package com.madhavth.daggermvvmapp.viewModels

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

    private val simpleData = MutableLiveData<String>()

    fun getData()
    {
        simpleData.value = repo.getData()
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