package com.example.checkerapp.ui.login

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import android.util.Patterns
import androidx.lifecycle.AndroidViewModel
import com.google.gson.Gson
import retrofit2.Call
import retrofit2.Response
import retrofit2.Callback

import com.example.checkerapp.R
import com.example.checkerapp.model.LoggedInUser
import com.example.checkerapp.model.ServerResponse
import com.example.checkerapp.repository.EmployeeApiRepository

class LoginViewModel(application: Application) : AndroidViewModel(application) {

    private val employeeApiRepository = EmployeeApiRepository()
    val serverResponse = MutableLiveData<String>()
    val error = MutableLiveData<String>()

//    private val _loginForm = MutableLiveData<LoginFormState>()
//    val loginFormState: LiveData<LoginFormState> = _loginForm
//
//    private val _loginResult = MutableLiveData<LoginResult>()
//    val loginResult: LiveData<LoginResult> = _loginResult



//    fun loginDataChanged(passId: String, password: String) {
//        if (!isPassIdValid(passId)) {
//            _loginForm.value = LoginFormState(passIdError = R.string.invalid_username)
//        } else if (!isPasswordValid(password)) {
//            _loginForm.value = LoginFormState(passwordError = R.string.invalid_password)
//        } else {
//            _loginForm.value = LoginFormState(isDataValid = true)
//        }
//    }

    fun login(loggedInUser: LoggedInUser) {
        employeeApiRepository.loginEmployee(loggedInUser).enqueue(object : Callback<ServerResponse> {
            override fun onResponse(call : Call<ServerResponse>, response: Response<ServerResponse>) {
                if (response.isSuccessful) serverResponse.value = "Succesvol"
                else {
                    error.value = "Geen geldige login"
                }
            }

            override fun onFailure(call: Call<ServerResponse>, t: Throwable) {
                error.value = t.message
            }

        })
    }


    private fun isPassIdValid(passId: String): Boolean {
        return if (passId.contains('@')) {
            Patterns.EMAIL_ADDRESS.matcher(passId).matches()
        } else {
            passId.isNotBlank()
        }
    }


    private fun isPasswordValid(password: String): Boolean {
        return password.length > 5
    }
}
