package com.example.checkerapp.ui.current

import android.app.Application
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.example.checkerapp.model.ServerResponse
import com.example.checkerapp.model.StatusHistory
import com.example.checkerapp.repository.EmployeeApiRepository
import com.google.gson.Gson
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class CurrentActivityViewModel(application: Application) : AndroidViewModel(application) {

    private val employeeApiRepository = EmployeeApiRepository()
    val statusHistory = MutableLiveData<StatusHistory>()
    val error = MutableLiveData<String>()
    var responseSuccess = false

    fun getCurrentStatusByEmployeeId(employeeId: Long) {
        employeeApiRepository.getCurrentStatusByEmployeeId(employeeId).enqueue(object :
            Callback<StatusHistory> {
            override fun onResponse(call : Call<StatusHistory>, response: Response<StatusHistory>) {
                if (response.isSuccessful) responseSuccess = true
                if (response.isSuccessful) statusHistory.value = response.body()!!
                else {
                    val serverResponse = Gson().fromJson(response.errorBody()!!.string()
                        , ServerResponse::class.java)
                    error.value = serverResponse.message
                }
            }

            override fun onFailure(call: Call<StatusHistory>, t: Throwable) {
                error.value = t.message
            }

        })
    }
}
