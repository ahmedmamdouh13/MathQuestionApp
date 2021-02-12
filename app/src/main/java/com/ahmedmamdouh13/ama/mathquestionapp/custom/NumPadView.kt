package com.ahmedmamdouh13.ama.mathquestionapp.custom

import android.view.View
import android.widget.Button
import com.ahmedmamdouh13.ama.mathquestionapp.R

class NumPadView(view: View) {
    private val one = view.findViewById<Button>(R.id.one_button)
    private val two = view.findViewById<Button>(R.id.two_button)
    private val three = view.findViewById<Button>(R.id.three_button)
    private val four = view.findViewById<Button>(R.id.four_button)
    private val five = view.findViewById<Button>(R.id.five_button)
    private val six = view.findViewById<Button>(R.id.six_button)
    private val seven = view.findViewById<Button>(R.id.seven_button)
    private val eight = view.findViewById<Button>(R.id.eight_button)
    private val nine = view.findViewById<Button>(R.id.nine_button)
    private val zero = view.findViewById<Button>(R.id.zero_button)

    private lateinit var onClickListener: View.OnClickListener


    fun onNumClicked(unit: (Int) -> Unit) {

        onClickListener = View.OnClickListener {
            val number = (it as Button).text.toString().toInt()
            unit(number)
        }

        initPads()

    }

    private fun initPads() {
        one.setOnClickListener(onClickListener)
        two.setOnClickListener(onClickListener)
        three.setOnClickListener(onClickListener)
        four.setOnClickListener(onClickListener)
        five.setOnClickListener(onClickListener)
        six.setOnClickListener(onClickListener)
        seven.setOnClickListener(onClickListener)
        eight.setOnClickListener(onClickListener)
        nine.setOnClickListener(onClickListener)
        zero.setOnClickListener(onClickListener)
    }


}