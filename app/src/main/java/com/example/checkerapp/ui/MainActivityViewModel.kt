package com.example.checkerapp.ui

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.example.checkerapp.model.Employee
import com.example.checkerapp.repository.EmployeeApiRepository
import retrofit2.Call
import retrofit2.Response
import retrofit2.Callback

class MainActivityViewModel(application: Application) : AndroidViewModel(application) {

    private val employeeApiRepository = EmployeeApiRepository()
    val employee = MutableLiveData<Employee>()
    val error = MutableLiveData<String>()

    fun getEmployeeByPassId(passId: Long) {
        employeeApiRepository.getEmployeeByPassId(passId).enqueue(object : Callback<Employee> {
            override fun onResponse(call : Call<Employee>, response: Response<Employee>) {
                if (response.isSuccessful) employee.value = response.body()!!
                else error.value = response.body().toString()
            }

            override fun onFailure(call: Call<Employee>, t: Throwable) {
                error.value = t.message
            }

        })
    }
}