package com.ahmedmamdouh13.ama.mathquestionapp.viewmodel

import androidx.lifecycle.ViewModel
import com.ahmedmamdouh13.ama.mathquestionapp.util.MathUtil
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(private val mathUtil: MathUtil) : ViewModel() {
    fun print(s: String) {
        mathUtil.printToo(s)
    }
}