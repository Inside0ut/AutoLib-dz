package com.clovertech.autolibdz.ViewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.clovertech.autolibdz.APIs.Couroutines
import com.clovertech.autolibdz.DataClasses.Vehicle
import com.clovertech.autolibdz.repository.CarsRepository
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch


class ViewModelCars (private val repository: CarsRepository): ViewModel() {
    private lateinit var job:Job
    private val myResponse= MutableLiveData<List<Vehicle>>()

    val carsByStat:LiveData<List<Vehicle>>
        get() = myResponse

      fun getListCarsByStat(Status: String){
         job= Couroutines.ioThenMain(
             {repository.getCarsByStat(Status)},{
                myResponse.value=it
             })
        /*val carsByStat=repository.getCarsByStat(Status)
        this.myResponse.value=carsByStat*/
    }
    fun getCarsByStatus(Status :String) {
        viewModelScope.launch {
            //val response: Response<Vehicle> = repository.getCarsListByState(Status)
        //    myResponse.value=response

        }
    }

    override fun onCleared() {
        super.onCleared()
        if(::job.isInitialized)job.cancel()
    }
}