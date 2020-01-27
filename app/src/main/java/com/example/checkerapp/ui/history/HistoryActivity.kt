package com.example.checkerapp.ui.history

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.core.view.ViewCompat
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.checkerapp.R
import com.example.checkerapp.adapter.HistoryAdapter
import com.example.checkerapp.model.StatusHistory
import com.example.checkerapp.ui.change.ChangeActivity
import com.example.checkerapp.ui.current.CurrentActivity
import kotlinx.android.synthetic.main.activity_history.*
import kotlinx.android.synthetic.main.navigation_bar.*

class HistoryActivity : AppCompatActivity() {

    private val statuses = arrayListOf<StatusHistory>()
    private val historyAdapter = HistoryAdapter(statuses)
    private lateinit var historyViewModel: HistoryActivityViewModel



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_history)

        initViews()
        initViewModel()
    }

    private fun initViews() {
        rvHistory.layoutManager = LinearLayoutManager(this@HistoryActivity, RecyclerView.VERTICAL,
            false)
        rvHistory.adapter = historyAdapter
        navigate()
    }

    private fun initViewModel() {
        historyViewModel = ViewModelProviders.of(this).get(HistoryActivityViewModel::class.java)
        historyViewModel.getStatusHistory(66366, 10)
        historyViewModel.history.observe(this, Observer {
                statuses.clear()
                statuses.addAll(it)
                historyAdapter.notifyDataSetChanged()
        })

        if(statuses.isEmpty()) {
            historyViewModel.error.observe(this, Observer {
                Toast.makeText(this, it, Toast.LENGTH_LONG).show()
            })
        }

    }

    private fun navigate(){
        btnChangeStatus.setOnClickListener { openChangeStatus() }
        btnCurrentStatus.setOnClickListener { openCurrentStatus() }
        btnHistory.setOnClickListener { openHistory() }
        ViewCompat.setBackgroundTintList(btnHistory, ContextCompat.getColorStateList(this, R.color.grayed_out_button))
        btnHistory.setTextColor(ContextCompat.getColorStateList(this, R.color.grayed_out_button_text))
    }

    private fun openHistory() {
        btnHistory.setOnClickListener {startActivity(
            Intent(this@HistoryActivity,
                HistoryActivity::class.java)
        )}
    }

    private fun openCurrentStatus() {
        btnCurrentStatus.setOnClickListener {startActivity(
            Intent(this@HistoryActivity,
                CurrentActivity::class.java)
        )}}

    private fun openChangeStatus() {
        btnChangeStatus.setOnClickListener {startActivity(
            Intent(this@HistoryActivity,
                ChangeActivity::class.java)
        )} }
}
