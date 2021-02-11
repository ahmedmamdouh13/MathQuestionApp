package com.ahmedmamdouh13.ama.mathquestionapp.view.fragment

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.LinearLayoutManager
import com.ahmedmamdouh13.ama.mathquestionapp.Constants
import com.ahmedmamdouh13.ama.mathquestionapp.R
import com.ahmedmamdouh13.ama.mathquestionapp.adapter.EquationsAdapter
import com.ahmedmamdouh13.ama.mathquestionapp.databinding.FragmentHomeBinding
import com.ahmedmamdouh13.ama.mathquestionapp.model.EquationModel
import com.ahmedmamdouh13.ama.mathquestionapp.view.Navigate
import com.ahmedmamdouh13.ama.mathquestionapp.viewmodel.MainViewModel
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class HomeFragment: Fragment(R.layout.fragment_home) {

    private lateinit var mainViewModel: MainViewModel
    private lateinit var equationsAdapter: EquationsAdapter
    lateinit var binder: FragmentHomeBinding
    private var equationMap: LinkedHashMap<Int, EquationModel> = linkedMapOf()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        binder = FragmentHomeBinding.bind(view)
        mainViewModel = ViewModelProvider(requireActivity()).get(MainViewModel::class.java)

        binder.addequationFabFragmenthome
            .setOnClickListener {
                Navigate.toCalculatorFragment(parentFragmentManager, R.id.main_container)
            }

        setUpRecyclerView()


        observeEquatiions()


        observeEquationResults()

    }

    private fun observeEquationResults() {


        mainViewModel._resultLiveData.observe(viewLifecycleOwner) { list ->

            list.forEachIndexed { i, it ->

                val jobId = it.outputData.getInt(Constants.jobId, -1)
                val result = it.outputData.getString("res")


                if (result != null) {
                    val equationModel = equationMap[jobId]
                    if (equationModel != null) {
                        val equation = equationModel.equation
                        equationModel.answer = "= $result"
                        equationMap[jobId] = equationModel

                        println("$equation = $result jobid: $jobId")
                        mainViewModel.cancelFinishedJob(it.id)
                    }
                }

            }
        }

    }

    private fun observeEquatiions() {
        mainViewModel._equationModelLiveData.observe(viewLifecycleOwner) {
            equationMap = it
            equationsAdapter.setNewList(equationMap)
        }
    }

    private fun setUpRecyclerView() {
        val recyclerView = binder.recyclerviewFragmenthome

        equationsAdapter = EquationsAdapter()
        equationsAdapter.setHasStableIds(true)
        recyclerView.layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
        recyclerView.itemAnimator = DefaultItemAnimator()
        recyclerView.adapter = equationsAdapter

    }
}