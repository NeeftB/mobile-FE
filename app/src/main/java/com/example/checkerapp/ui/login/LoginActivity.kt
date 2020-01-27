package com.example.checkerapp.ui.login

import android.content.Intent
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity

import android.widget.Toast

import com.example.checkerapp.R
import com.example.checkerapp.model.LoggedInUser
import com.example.checkerapp.ui.current.CurrentActivity
import com.example.checkerapp.ui.register.RegisterActivity
import kotlinx.android.synthetic.main.activity_login.*

class LoginActivity : AppCompatActivity() {

    private lateinit var loginViewModel: LoginViewModel

        override fun onCreate(savedInstanceState: Bundle?) {
            super.onCreate(savedInstanceState)
            setContentView(R.layout.activity_login)

            initViews()
        }

    private fun initViews() {
        btnLogin.setOnClickListener{loginEmployee()}
        btnGeenAccount.setOnClickListener {startActivity(
            Intent(this@LoginActivity,
                RegisterActivity::class.java)
        )}
    }


    private fun loginEmployee() {
        val user = LoggedInUser(
            passId = passId.text.toString(),
            password = password.text.toString()
        )

        loginViewModel = ViewModelProviders.of(this).get(LoginViewModel::class.java)
        loginViewModel.login(user)


        if(user != null){
            loginViewModel.serverResponse.observe(this, Observer {
                Toast.makeText(this, it, Toast.LENGTH_LONG).show()
            })

            btnLogin.setOnClickListener {startActivity(
                Intent(this@LoginActivity,
                    CurrentActivity::class.java)
            )}

        }else{

            loginViewModel.error.observe(this, Observer {
                Toast.makeText(this, it, Toast.LENGTH_LONG).show()
            })
        }

        }



    }

