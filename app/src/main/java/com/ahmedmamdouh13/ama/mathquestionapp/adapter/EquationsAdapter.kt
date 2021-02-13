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
import kotlinx.coroutines.delay

class EquationsAdapter :
    RecyclerView.Adapter<EquationsAdapter.EquationsViewHolder>() {

    private var counter: CountDownTimer? = null
    private var list: LinkedHashMap<Int, EquationModel> = linkedMapOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EquationsViewHolder {
        val itemView = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_equation, null, false)

        return EquationsViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: EquationsViewHolder, position: Int) {
        holder.bind(list.values.toList()[position])
    }

    override fun getItemCount(): Int = list.size

    fun setNewList(list: LinkedHashMap<Int, EquationModel>) {
        this.list = list
        setDelayedTimeCounter()
        notifyDataSetChanged()
    }
    private var isInitialized: Boolean = false

    fun setDelayedTimeCounter() {

            counter?.cancel()


            counter = object : CountDownTimer(31000, 1000) {
                override fun onTick(p0: Long) {
                    if (list.isNotEmpty()) {
                        for (l in 0 until list.size) {
                            var delayed = list.values.toList()[l].delayed
                            delayed--
                            list.values.toList()[l].delayed = delayed
                        }
                        notifyDataSetChanged()
                    }
                }

                override fun onFinish() {
                }

            }.start()

    }
    fun disposeTimer(){
        counter?.cancel()
    }

    inner class EquationsViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        private val timerTextView = itemView.findViewById<TextView>(R.id.timer_textview)
        private val equationTextView = itemView.findViewById<TextView>(R.id.equation_textview)
        private val customProgressView = itemView.findViewById<CustomProgressView>(R.id.customprogress)
        private val answerTextView = itemView.findViewById<TextView>(R.id.answer_textview)

        private val colorRed =
            ContextCompat.getColor(itemView.context, R.color.pad_color)
        private val colorGreen =
            ContextCompat.getColor(itemView.context, R.color.semiblack)

        fun bind(equationModel: EquationModel) {

            equationTextView.text = equationModel.equation
            answerTextView.text = equationModel.answer
            val delayed = equationModel.delayed

            when {
                delayed > 0L -> {
                    timerTextView.text = delayed.toString()
                    customProgressView.setProgress(delayed.toFloat())
                    customProgressView.setColor(colorRed)
//                    timerTextView.setTextColor(colorGreen)
                    answerTextView.visibility = View.GONE
                }
                else -> {
                    timerTextView.text = "Done"
//                    timerTextView.setTextColor(colorRed)
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