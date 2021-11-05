package com.srilaxmi.fitbodchallenge

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment

class MainActivity : AppCompatActivity(), ExcerciseListFragment.excerciseSelectedListener{
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        addFragment(ExcerciseListFragment())
    }

    fun addFragment(fragment: Fragment){
        supportFragmentManager
            .beginTransaction()
            .replace(R.id.fragmentcontainer, fragment)
            .commit()
    }

    override fun onExcerciseSelected(name: String, dataMap: MutableMap<String, Int>) {
        addFragment(ExerciseChartFragment.newInstance(name, dataMap))
    }
}