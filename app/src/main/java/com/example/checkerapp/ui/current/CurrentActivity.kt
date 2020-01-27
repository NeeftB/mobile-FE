package com.example.checkerapp.ui.current

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.core.view.ViewCompat
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.example.checkerapp.R
import com.example.checkerapp.ui.change.ChangeActivity
import com.example.checkerapp.ui.history.HistoryActivity
import kotlinx.android.synthetic.main.activity_current.*
import kotlinx.android.synthetic.main.navigation_bar.*

class CurrentActivity : AppCompatActivity() {

    private lateinit var currentActivityViewModel: CurrentActivityViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_current)

        initViews()
        initViewModel()
    }

    private fun initViews() {
        navigate()
    }

    private fun initViewModel() {
        currentActivityViewModel = ViewModelProviders.of(this).get(CurrentActivityViewModel::class.java)
        currentActivityViewModel.getCurrentStatusByEmployeeId(66366)
        currentActivityViewModel.statusHistory.observe(this, Observer { statusHistory ->
            if (statusHistory != null) {
                getEmployee(statusHistory.status)
                tvActualStatusText.text = getString(R.string.checked_status, statusHistory.status)
            }
        })

        currentActivityViewModel.error.observe(this, Observer {
            Toast.makeText(this, it, Toast.LENGTH_LONG).show()
        })

    }

    private fun navigate(){
        btnChangeStatus.setOnClickListener { openChangeStatus() }
        btnCurrentStatus.setOnClickListener { openCurrentStatus() }
        btnHistory.setOnClickListener { openHistory() }
        ViewCompat.setBackgroundTintList(btnCurrentStatus, ContextCompat.getColorStateList(this, R.color.grayed_out_button))
        btnCurrentStatus.setTextColor(ContextCompat.getColorStateList(this, R.color.grayed_out_button_text))
    }

    private fun openHistory() {
        btnHistory.setOnClickListener {startActivity(
            Intent(this@CurrentActivity,
                HistoryActivity::class.java)
        )}
    }

    private fun openCurrentStatus() {
        btnCurrentStatus.setOnClickListener {startActivity(
            Intent(this@CurrentActivity,
                CurrentActivity::class.java)
        )}}

    private fun openChangeStatus() {
        btnChangeStatus.setOnClickListener {startActivity(
            Intent(this@CurrentActivity,
                ChangeActivity::class.java)
        )} }

    private fun getEmployee(statusHistory: String) {

        when (statusHistory) {
            "in" -> {
                ivActualStatusPlaceHolder.setBackgroundResource(R.drawable.vinkje)
                clActualStatus.setBackgroundColor(ContextCompat.getColor(this, R.color.checked_in))
                // and more color changes
            }
            "out" -> {
                ivActualStatusPlaceHolder.setBackgroundResource(R.drawable.kruis)
                clActualStatus.setBackgroundColor(ContextCompat.getColor(this, R.color.checked_out))
                // change colors to red-ish
            }
            else -> Toast.makeText(applicationContext,"Something went wrong \n guess you entered limbo...",
                Toast.LENGTH_SHORT).show()
        }

    }
}
