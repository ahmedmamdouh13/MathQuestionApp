package com.ahmedmamdouh13.ama.mathquestionapp.adapter

import android.os.CountDownTimer
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.ahmedmamdouh13.ama.mathquestionapp.R
import com.ahmedmamdouh13.ama.mathquestionapp.custom.CustomProgressView
import com.ahmedmamdouh13.ama.mathquestionapp.model.EquationModel

class EquationsAdapter :
    RecyclerView.Adapter<EquationsAdapter.EquationsViewHolder>() {

    private var list: LinkedHashMap<Int, EquationModel> = linkedMapOf()
    private val cntDown: ArrayList<Long> = arrayListOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EquationsViewHolder {
        val itemView = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_equation, null, false)

        return EquationsViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: EquationsViewHolder, position: Int) {
        holder.bind(list.values.toList()[position], position)
    }

    override fun getItemCount(): Int = list.size

    fun setNewList(list: LinkedHashMap<Int, EquationModel>) {
        this.list = list
        setDelayedTimeCounter()
    }

    private fun setDelayedTimeCounter() {
        list.values.toList().forEach {
            cntDown.add(it.delayed)
        }

        object : CountDownTimer(30000, 1000) {
            override fun onTick(p0: Long) {
                for (l in 0 until cntDown.size) {
                    cntDown[l]--
                    list[l]?.delayed = cntDown[l]
                }
                notifyDataSetChanged()
            }

            override fun onFinish() {

            }

        }.start()

    }

    inner class EquationsViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        private val timerTextView = itemView.findViewById<TextView>(R.id.timer_textview)
        private val equationTextView = itemView.findViewById<TextView>(R.id.equation_textview)
        private val customProgressView = itemView.findViewById<CustomProgressView>(R.id.customprogress)
        private val answerTextView = itemView.findViewById<TextView>(R.id.answer_textview)

        private val colorRed =
            ContextCompat.getColor(itemView.context, android.R.color.holo_green_dark)
        private val colorGreen =
            ContextCompat.getColor(itemView.context, R.color.semiblack)

        fun bind(equationModel: EquationModel, pos: Int) {

            equationTextView.text = equationModel.equation
            answerTextView.text = equationModel.answer

            when {
                cntDown[pos] > 0L -> {
                    timerTextView.text = cntDown[pos].toString()
                    customProgressView.setProgress(cntDown[pos].toFloat())
                    customProgressView.setColor(colorRed)
                    timerTextView.setTextColor(colorGreen)
                    answerTextView.visibility = View.GONE
                }
                else -> {
                    timerTextView.text = "Done"
                    timerTextView.setTextColor(colorRed)
                    customProgressView.setProgress(1f)
                    customProgressView.setColor(colorGreen)
                    answerTextView.visibility = View.VISIBLE
                }
            }

        }


    }

    override fun getItemId(position: Int): Long {
        return list.values.toList()[position].jobId.toLong()
    }


}