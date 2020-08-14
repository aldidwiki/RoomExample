package com.aldidwikip.roomexamples.ui.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.aldidwikip.roomexamples.R
import com.aldidwikip.roomexamples.data.model.Word
import com.aldidwikip.roomexamples.databinding.RecyclerviewItemBinding

class WordListAdapter internal constructor(context: Context) :
        ListAdapter<Word, WordListAdapter.WordViewHolder>(DIFF_CALLBACK) {

    private val inflater: LayoutInflater = LayoutInflater.from(context)
    private lateinit var onItemClickCallback: OnItemClickCallback

    fun setOnItemClickCallback(onItemClickCallback: OnItemClickCallback) {
        this.onItemClickCallback = onItemClickCallback
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): WordViewHolder {
        val itemBinding: RecyclerviewItemBinding =
                DataBindingUtil.inflate(inflater, R.layout.recyclerview_item, parent, false)
        return WordViewHolder(itemBinding)
    }

    override fun onBindViewHolder(holder: WordViewHolder, position: Int) {
        val current = getItem(position)
        with(holder) {
            bind(current)

            itemView.setOnClickListener {
                onItemClickCallback.onItemClick(getItem(adapterPosition))
            }
        }
    }

    inner class WordViewHolder(private val binding: RecyclerviewItemBinding)
        : RecyclerView.ViewHolder(binding.root) {

        fun bind(data: Word) {
            binding.apply {
                wordData = data
                executePendingBindings()
            }
        }
    }

    interface OnItemClickCallback {
        fun onItemClick(data: Word)
    }

    companion object {
        private val DIFF_CALLBACK = object : DiffUtil.ItemCallback<Word>() {
            override fun areItemsTheSame(oldItem: Word, newItem: Word): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(oldItem: Word, newItem: Word): Boolean {
                return oldItem == newItem
            }
        }
    }
}