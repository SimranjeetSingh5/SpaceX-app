package com.example.spacexapp.viewmodels

import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.spacexapp.models.Rocket
import com.example.spacexapp.repository.RocketRepository
import com.example.spacexapp.utils.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import retrofit2.Response
import javax.inject.Inject

@HiltViewModel
class RocketViewModel @Inject constructor(private val repository: RocketRepository):ViewModel(),
    LifecycleObserver {


    val rockets: MutableLiveData<Resource<List<Rocket?>>?> = MutableLiveData()

    init {
        getRockets()
    }

     fun getRockets() =
         viewModelScope.launch {
             rockets.postValue(Resource.Loading())
             val response = repository.getRockets()
             rockets.postValue(handleRocketsResponse(response))
         }

    private fun handleRocketsResponse(response: Response<List<Rocket>>): Resource<List<Rocket?>> {
        if (response.isSuccessful){

            response.body()?.let{rocketResponse->

                return  Resource.Success(rocketResponse)
            }
        }

        return  Resource.Error(response.message())
    }








//    fun saveRocket(rocket: List<Rocket>) = viewModelScope.launch {
//        repository.insertRocketToRoom(rocket)
//        }
//    fun getSavedRockets() = repository.getSavedRockets()
//
//    fun deleteRocket(rocket: Rocket) = viewModelScope.launch {
//        repository.deleteRocket(rocket)
//    }
    }
