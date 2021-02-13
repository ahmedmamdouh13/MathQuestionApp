package com.ahmedmamdouh13.ama.mathquestionapp.view

import android.os.Bundle
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.FragmentContainerView
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.ahmedmamdouh13.ama.mathquestionapp.Constants
import com.ahmedmamdouh13.ama.mathquestionapp.R
import com.ahmedmamdouh13.ama.mathquestionapp.adapter.EquationItemDecorator
import com.ahmedmamdouh13.ama.mathquestionapp.adapter.EquationsAdapter
import com.ahmedmamdouh13.ama.mathquestionapp.model.EquationModel
import com.ahmedmamdouh13.ama.mathquestionapp.viewmodel.MainViewModel
import com.google.android.material.floatingactionbutton.FloatingActionButton
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var locationButton: ImageView
    private lateinit var recyclerview: RecyclerView
    private lateinit var fab: FloatingActionButton
    private lateinit var mainContainer: FragmentContainerView
    private lateinit var mainViewModel: MainViewModel
    private lateinit var placeHolder: TextView

    private lateinit var equationsAdapter: EquationsAdapter
    private var equationMap: LinkedHashMap<Int, EquationModel> = LinkedHashMap()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        mainViewModel = ViewModelProvider(this).get(MainViewModel::class.java)

        fab = findViewById(R.id.addequation_fab)
        recyclerview = findViewById(R.id.recyclerview)
        mainContainer = findViewById(R.id.main_container)
        locationButton = findViewById(R.id.location_imageview_mainactivity)
        placeHolder = findViewById(R.id.placeholder_mainactivty)


        setOnAddClicked()

        setUpRecyclerView()

        observeEquations()

        observeEquationResults()

        setOnLocationButtonClicked()

        listenToLocationChanges()
    }

    private fun setOnAddClicked() {
        fab.setOnClickListener {
            Navigate.toCalculatorFragment(supportFragmentManager, R.id.main_container)
            mainVisibility(false)
        }
    }

    private fun mainVisibility(isVisible: Boolean) {
        if (!isVisible) {
            mainContainer.visibility = View.VISIBLE
            fab.visibility = View.GONE
            locationButton.visibility = View.GONE

        } else {
            mainContainer.visibility = View.GONE
            fab.visibility = View.VISIBLE
            locationButton.visibility = View.VISIBLE
        }
    }

    private fun listenToLocationChanges() {
        mainViewModel._locationInfoLiveData.observe(this) {
            Toast.makeText(this, it, Toast.LENGTH_LONG).show()
        }
    }

    private fun setOnLocationButtonClicked() {
        locationButton.setOnClickListener {
            println("location?!!!!!")
            mainViewModel.getLocationInfo(this)
        }
    }

    private fun observeEquationResults() {


        mainViewModel._resultLiveData.observe(this) { list ->

            list.forEachIndexed { i, it ->

                val jobId = it.outputData.getInt(Constants.jobId, -1)
                val result = it.outputData.getString("res")


                if (result != null) {
                    val equationModel = equationMap[jobId]
                    if (equationModel != null) {
                        val equation = equationModel.equation
                        equationModel.answer = "= $result"
                        equationMap[jobId] = equationModel

                        equationsAdapter.notifyDataSetChanged()
                        println("$equation = $result jobid: $jobId")
                        mainViewModel.cancelFinishedJob(it.id)
                    }
                }

            }
        }

    }

    private fun observeEquations() {
        mainViewModel._equationModelLiveData.observe(this) {
            equationMap[it.jobId] = it
            equationsAdapter.notifyDataSetChanged()
            equationsAdapter.setDelayedTimeCounter()
            mainVisibility(true)
            placeHolder.visibility = View.GONE
        }
    }

    private fun setUpRecyclerView() {
        equationsAdapter = EquationsAdapter()
        equationsAdapter.setHasStableIds(true)
        equationsAdapter.setNewList(equationMap)
        recyclerview.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        recyclerview.itemAnimator = DefaultItemAnimator()
        recyclerview.addItemDecoration(EquationItemDecorator())
        recyclerview.adapter = equationsAdapter
    }

    override fun onDestroy() {
        super.onDestroy()
        mainViewModel.cancelAllJobs()
        equationsAdapter.disposeTimer()
    }

    override fun onBackPressed() {
        if (mainContainer.visibility == View.VISIBLE) {
            mainVisibility(true)
        } else
            super.onBackPressed()
    }

}