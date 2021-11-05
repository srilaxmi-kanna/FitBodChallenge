package com.srilaxmi.fitbodchallenge

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.db.williamchart.view.LineChartView

class ExerciseChartFragment : Fragment() {

    lateinit private var name: String
    lateinit private var dataMap: MutableMap<String, Int>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_chart_exercise, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val linkedMap = linkedMapOf<String, Float>()
        for(key in dataMap.keys){
            val date = key.split(" ")
            linkedMap.put(date[0] + " " + date[1], dataMap[key]?.toFloat() ?: 0F)
        }
        Log.e("TEST", linkedMap.toString())
        view.findViewById<LineChartView>(R.id.pr_chart).show(linkedMap)
        view.findViewById<TextView>(R.id.exercise_name).text = name
    }
    companion object {

        @JvmStatic
        fun newInstance(name: String, dataMap: MutableMap<String, Int>) =
            ExerciseChartFragment().apply {
                this.name = name
                this.dataMap = dataMap
            }
    }
}