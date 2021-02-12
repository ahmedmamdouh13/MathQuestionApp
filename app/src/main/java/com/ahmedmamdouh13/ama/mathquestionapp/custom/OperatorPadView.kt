package com.ahmedmamdouh13.ama.mathquestionapp.custom

import android.view.View
import android.widget.Button
import com.ahmedmamdouh13.ama.mathquestionapp.R

class OperatorPadView(view: View) {
    private val multiply = view.findViewById<Button>(R.id.multiply_button)
    private val plus = view.findViewById<Button>(R.id.plus_button)
    private val divide = view.findViewById<Button>(R.id.divide_button)
    private val minus = view.findViewById<Button>(R.id.minus_button)


    private lateinit var onClickListener: View.OnClickListener


    fun onOperatorPadClicked(unit: (String) -> Unit) {

        onClickListener = View.OnClickListener {
            val op = (it as Button).text.toString()
            unit(op)
        }

        initPads()
    }

    private fun initPads() {
        multiply.setOnClickListener(onClickListener)
        plus.setOnClickListener(onClickListener)
        divide.setOnClickListener(onClickListener)
        minus.setOnClickListener(onClickListener)
    }


}