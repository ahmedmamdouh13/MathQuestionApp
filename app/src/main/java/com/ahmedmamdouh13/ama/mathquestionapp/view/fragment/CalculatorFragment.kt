package com.ahmedmamdouh13.ama.mathquestionapp.view.fragment

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.ahmedmamdouh13.ama.mathquestionapp.Constants
import com.ahmedmamdouh13.ama.mathquestionapp.R
import com.ahmedmamdouh13.ama.mathquestionapp.custom.NumPadView
import com.ahmedmamdouh13.ama.mathquestionapp.custom.OperatorPadView
import com.ahmedmamdouh13.ama.mathquestionapp.databinding.FragmentCalculatorBinding
import com.ahmedmamdouh13.ama.mathquestionapp.mapper.OperatorMapper
import com.ahmedmamdouh13.ama.mathquestionapp.util.EquationValidation
import com.ahmedmamdouh13.ama.mathquestionapp.view.Navigate
import com.ahmedmamdouh13.ama.mathquestionapp.viewmodel.MainViewModel
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class CalculatorFragment : Fragment(R.layout.fragment_calculator) {

    @Inject
    lateinit var equationValidation: EquationValidation

    @Inject
    lateinit var mapper: OperatorMapper

    private lateinit var binder: FragmentCalculatorBinding

    private val operators = arrayListOf<Int>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        binder = FragmentCalculatorBinding.bind(view)

        val mainViewModel = ViewModelProvider(requireActivity()).get(MainViewModel::class.java)


        NumPadView(binder.numPad.root).onNumClicked {
            binder.equationTextviewFragmentcalculator.append(it.toString())
        }

        OperatorPadView(binder.operatorPad.root).onOperatorPadClicked {
            if (validateOperators(it)) {
                val text = binder.equationTextviewFragmentcalculator.text
                if (text.isNotEmpty() && text.last().isDigit()) {
                    binder.equationTextviewFragmentcalculator.append(it)
                } else {
                    Toast.makeText(context, "Can't have an operator in the beginning or beside another.", Toast.LENGTH_SHORT).show()
                }
            } else {
                Toast.makeText(context, "Only two types of operators allowed.", Toast.LENGTH_SHORT).show()
            }
        }


        binder.nextFabFragmencalculator
            .setOnClickListener {

                val equation = binder.equationTextviewFragmentcalculator.text.toString()
//                isEquationValid(equation)

                if (equationValidation.validateEquation(equation)) {

                    Navigate.toTimerFragment(
                        parentFragmentManager,
                        R.id.main_container,
                        equation,
                        getOp1(),
                        getOp2()
                    )
                    operators.clear()
                }
                else Toast.makeText(context, "Invalid Equation.", Toast.LENGTH_SHORT).show()
            }


    }

    private fun getOp1(): Int {
        return operators[0]
    }

    private fun getOp2(): Int {
        return if (operators.size > 1)
            operators[1]
        else
            operators[0]
    }

    private fun validateOperators(it: String): Boolean {
        val flagFromOperator = mapper.getFlagFromOperator(it)
        return if (operators.isNotEmpty()) {
            if (!operators.contains(flagFromOperator)) {
                if (operators.size > 1)
                    false
                else {
                    operators.add(flagFromOperator)
                    true
                }
            } else true
        } else {
            operators.add(flagFromOperator)
            true
        }
    }


}