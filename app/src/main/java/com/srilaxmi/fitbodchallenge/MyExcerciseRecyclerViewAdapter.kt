package com.srilaxmi.fitbodchallenge

import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView

/**
 * [RecyclerView.Adapter] that can display a [DummyItem].
 * TODO: Replace the implementation with code for your data type.
 */
class MyExcerciseRecyclerViewAdapter(
    private val values: MutableMap<String,MutableMap<String, Int>>,
    val listener: ExcerciseListFragment.excerciseSelectedListener
) : RecyclerView.Adapter<MyExcerciseRecyclerViewAdapter.ViewHolder>() {

    val keys = values.keys.toTypedArray()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.fragment_excercise_item, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val key = keys[position]
        holder.contentView.text = key
        holder.contentView.setOnClickListener {
            listener.onExcerciseSelected(key, values[key] ?: mutableMapOf() )
        }
    }

    override fun getItemCount(): Int = values.size

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val contentView: TextView = view.findViewById(R.id.content)

        override fun toString(): String {
            return super.toString() + " '" + contentView.text + "'"
        }
    }
}