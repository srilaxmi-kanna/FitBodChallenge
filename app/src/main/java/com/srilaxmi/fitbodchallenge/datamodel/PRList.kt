package com.srilaxmi.fitbodchallenge.datamodel

import android.content.Context
import android.util.Log
import com.srilaxmi.fitbodchallenge.R
import java.io.BufferedReader
import java.io.IOException
import java.io.InputStream
import java.io.InputStreamReader
import java.nio.charset.Charset


class PRList(val context: Context) {

    lateinit var prMap: MutableMap<String, MutableMap<String, Int>>

    fun readData() {
        val inputStream: InputStream = context.resources.openRawResource(R.raw.workout_data)
        val reader = BufferedReader(
            InputStreamReader(inputStream, Charset.forName("UTF-8"))
        )
        prMap = mutableMapOf()
        var line = reader.readLine()
        try {
            while (line != null) {
                val tokens = line.split(",").toTypedArray()

                val exercise: Exercise = Exercise()
                exercise.workoutDate = tokens[0]
                exercise.execiseName = tokens[1]
                exercise.sets = tokens[2].toInt()
                exercise.reps = tokens[3].toInt()
                exercise.weight = tokens[4].toInt()

                val rm = find1RM(exercise)
                addToList(exercise, rm)
                line = reader.readLine()
            }
        } catch (e1: IOException) {
            Log.e("MainActivity", "Error$line", e1)
            e1.printStackTrace()
        }
    }

   private fun find1RM(exercise: Exercise): Int = exercise.weight * 36 / (37-exercise.reps)

   private fun addToList(excercise: Exercise, rm: Int){
       if(prMap.containsKey(excercise.execiseName)){
           val dateMap = prMap[excercise.execiseName]
           dateMap?.getOrPut(excercise.workoutDate) {rm}
           if(dateMap?.get(excercise.workoutDate) ?: 0 < rm){
               dateMap?.put(excercise.workoutDate, rm)
           }
       }
       else{
           val dateMap = mutableMapOf(excercise.workoutDate to rm)
           prMap.put(excercise.execiseName, dateMap)
       }
   }
}