package com.innocent.crop2cashassessment

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.ProgressBar
import android.widget.Toast
import androidx.activity.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.innocent.crop2cashassessment.ui.ExhibitListViewState
import com.innocent.crop2cashassessment.ui.ExhibitViewModel
import com.innocent.crop2cashassessment.ui.adapter.ExhibitAdapter
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.InternalCoroutinesApi
import kotlinx.coroutines.launch

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private val viewModel: ExhibitViewModel  by viewModels()
    private lateinit var exhibitListAdapter: ExhibitAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val recyclerView = findViewById<RecyclerView>(R.id.exhibit_recyclerview)
        val progressBar = findViewById<ProgressBar>(R.id.progressBar)

            viewModel.viewState.observe(this, Observer {
                when(it){
                    is ExhibitListViewState.Success -> progressBar.visibility = View.GONE
                    ExhibitListViewState.Loading -> progressBar.visibility = View.VISIBLE
                }
            })

        viewModel.exhibits.observe(this, Observer {
            exhibitListAdapter = ExhibitAdapter(it)
            recyclerView.adapter = exhibitListAdapter
        })

        recyclerView.layoutManager = LinearLayoutManager(this)

    }
}