package com.example.checkerapp.ui.change

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.core.view.ViewCompat
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.example.checkerapp.R
import com.example.checkerapp.model.StatusChange
import com.example.checkerapp.ui.current.CurrentActivity
import com.example.checkerapp.ui.history.HistoryActivity
import kotlinx.android.synthetic.main.activity_change_status.*
import kotlinx.android.synthetic.main.navigation_bar.*

class ChangeActivity : AppCompatActivity() {
    
    private lateinit var changeActivityViewModel: ChangeActivityViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_change_status)

        initViews()
        initViewModel()
    }

    private fun initViews() {
        navigate()
        btnChangeState.setOnClickListener{changeState()}
    }

    private fun initViewModel() {
        changeActivityViewModel = ViewModelProviders.of(this).get(ChangeActivityViewModel::class.java)
        changeActivityViewModel.getCurrentStatusByEmployeeId(66366)
        changeActivityViewModel.statusHistory.observe(this, Observer { statusHistory ->
            if (statusHistory != null) {
                getEmployee(statusHistory.status)
                tvCurrentStatusText.text = getString(R.string.checked_status, statusHistory.status)
            }
        })

        changeActivityViewModel.error.observe(this, Observer {
            Toast.makeText(this, it, Toast.LENGTH_LONG).show()
        })

    }

    private fun getEmployee(statusHistory: String) {

        when (statusHistory) {
            "in" -> {
                ivStatusPlaceHolder.setBackgroundResource(R.drawable.vinkje)
                clChangeStatus.setBackgroundColor(ContextCompat.getColor(this, R.color.checked_in))
                btnChangeState.setBackgroundColor(ContextCompat.getColor(this, R.color.colorAccent))
                btnChangeState.text = getString(R.string.check_me_out)
                etReason.isEnabled = true
                etReason.hint = getString(R.string.reason_field_optional)
                tilReason.background = getDrawable(R.drawable.edit_text_background)
                // and more color changes
            }
            "out" -> {
                ivStatusPlaceHolder.setBackgroundResource(R.drawable.kruis)
                clChangeStatus.setBackgroundColor(ContextCompat.getColor(this, R.color.checked_out))
                btnChangeState.setBackgroundColor(ContextCompat.getColor(this, R.color.grayed_out))
                btnChangeState.text = getString(R.string.check_me_in)
                etReason.isEnabled = false
                etReason.hint = getString(R.string.reason_field_to_check_in)
                tilReason.background = getDrawable(R.drawable.edit_text_background_greyed)
                // change colors to red-ish
            }
            else -> Toast.makeText(applicationContext,"Something went wrong \n guess you entered limbo...",Toast.LENGTH_SHORT).show()
        }

    }

    private fun changeState() {
        val statusChange = StatusChange(
            reason = etReason.text.toString()
        )

        changeActivityViewModel.changeStatus(66366, statusChange)

        finish()
        startActivity(getIntent())

        if (changeActivityViewModel.responseSuccess) {
            Toast.makeText(this, "it worked", Toast.LENGTH_SHORT).show()
            //TODO deze toast omzetten naar de response van de server.

        }
        else {
            changeActivityViewModel.error.observe(this, Observer {
                Toast.makeText(this, it, Toast.LENGTH_LONG).show()
            })
        }
    }

    private fun navigate(){
        btnChangeStatus.setOnClickListener { openChangeStatus() }
        btnCurrentStatus.setOnClickListener { openCurrentStatus() }
        btnHistory.setOnClickListener { openHistory() }
        ViewCompat.setBackgroundTintList(btnChangeStatus, ContextCompat.getColorStateList(this, R.color.grayed_out_button))
        btnChangeStatus.setTextColor(ContextCompat.getColorStateList(this, R.color.grayed_out_button_text))
    }

    private fun openHistory() {
        btnHistory.setOnClickListener {startActivity(
            Intent(this@ChangeActivity,
                HistoryActivity::class.java)
        )}
    }

    private fun openCurrentStatus() {
        btnCurrentStatus.setOnClickListener {startActivity(
            Intent(this@ChangeActivity,
                CurrentActivity::class.java)
        )}}

    private fun openChangeStatus() {
        btnChangeStatus.setOnClickListener {startActivity(
            Intent(this@ChangeActivity,
                ChangeActivity::class.java)
        )} }
}