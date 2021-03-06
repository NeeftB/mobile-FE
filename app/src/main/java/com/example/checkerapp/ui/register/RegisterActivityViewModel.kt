package com.example.checkerapp.ui.register

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.example.checkerapp.model.Employee
import com.example.checkerapp.model.ServerResponse
import com.example.checkerapp.repository.EmployeeApiRepository
import com.google.gson.Gson
import retrofit2.Call
import retrofit2.Response
import retrofit2.Callback

class RegisterActivityViewModel(application: Application) : AndroidViewModel(application) {

    private val employeeApiRepository = EmployeeApiRepository()
    var authResponse:Boolean = false
    val error = MutableLiveData<String>()
    val serverResponse = MutableLiveData<String>()

    fun registerEmployee(employee: Employee) {
        employeeApiRepository.registerEmployee(employee).enqueue(object : Callback<ServerResponse> {

           override fun onFailure(call: Call<ServerResponse>, t: Throwable) {
                error.value = t.message
            }

            override fun onResponse(call : Call<ServerResponse>, response: Response<ServerResponse>) {
                if (response.isSuccessful) authResponse = true
                else {
                    val serverResponse = Gson().fromJson(response.errorBody()!!.string()
                        , ServerResponse::class.java)
                    error.value = serverResponse.message
                }
            }
        })
    }
}