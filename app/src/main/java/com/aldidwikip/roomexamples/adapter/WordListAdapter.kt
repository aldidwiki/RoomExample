package com.aldidwikip.roomexamples.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.aldidwikip.roomexamples.R
import com.aldidwikip.roomexamples.repository.Word

class WordListAdapter internal constructor(context: Context) : RecyclerView.Adapter<WordListAdapter.WordViewHolder>() {

    private val inflater: LayoutInflater = LayoutInflater.from(context)
    private var words = emptyList<Word>()
    private lateinit var onItemClickCallback: OnItemClickCallback

    fun setOnItemClickCallback(onItemClickCallback: OnItemClickCallback) {
        this.onItemClickCallback = onItemClickCallback
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): WordViewHolder {
        val itemView = inflater.inflate(R.layout.recyclerview_item, parent, false)
        return WordViewHolder(itemView)
    }

    override fun getItemCount() = words.size

    override fun onBindViewHolder(holder: WordViewHolder, position: Int) {
        val current = words[position]
        with(holder) {
            wordItemView.text = current.name
            jobItemView.text = current.job
            cityItemView.text = current.city

            itemView.setOnClickListener {
                onItemClickCallback.onItemClick(words[adapterPosition])
            }
        }
    }

    internal fun setWords(words: List<Word>) {
        this.words = words
        notifyDataSetChanged()
    }

    inner class WordViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val wordItemView: TextView = itemView.findViewById(R.id.tv_name)
        val jobItemView: TextView = itemView.findViewById(R.id.tv_job)
        val cityItemView: TextView = itemView.findViewById(R.id.tv_city)
    }

    interface OnItemClickCallback {
        fun onItemClick(data: Word)
    }
}