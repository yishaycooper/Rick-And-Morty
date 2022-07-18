package com.example.rickandmorty
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.rickandmorty.network.ApiClient
import com.example.rickandmorty.network.CharacterResponse
import androidx.lifecycle.viewModelScope
import com.example.rickandmorty.network.Location
import kotlinx.coroutines.launch


class SharedViewModel: ViewModel() {

    private val _characters = MutableLiveData<CharacterResponse>()
    val characters: LiveData<CharacterResponse> = _characters

    private val _location = MutableLiveData<Location>()
    val location: LiveData<Location> = _location


    fun fetchLocation(characterId: Int) {
        viewModelScope.launch {
            val response = ApiClient.locationService.fetchLocation(characterId)

            _location.postValue(response.body())
        }

    }

// USING COROUTINES
    fun fetchCharacters(characterPage: String) {
        viewModelScope.launch {

            val response = ApiClient.apiService.fetchCharacters(characterPage)
            _characters.postValue(response.body())

        }

    }

// USING CALL
//        val client = ApiClient.apiService.fetchCharacters("1")
//            .enqueue(object : Callback<CharacterResponse> {
//
//            override fun onResponse(
//                call: Call<CharacterResponse>,
//                response: Response<CharacterResponse>) {
//
//                if(response.isSuccessful) {
//                    _characters.postValue(response.body())
//                } else {
//                    _characters.postValue(null)
//                }
//
//            }
//
//            override fun onFailure(
//                call: Call<CharacterResponse>,
//                t: Throwable) {
//                Log.d("failed", "" + t.message)
//                _characters.postValue(null)
//            }
//        })

}