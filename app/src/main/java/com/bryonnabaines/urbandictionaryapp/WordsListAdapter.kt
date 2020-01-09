package com.bryonnabaines.urbandictionaryapp

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bryonnabaines.urbandictionaryapp.api.Word
import kotlinx.android.synthetic.main.item_words_list.view.*

/**
 * created by bryonnabaines on 2020-01-07
 */

class WordsListAdapter(
    val clickListener: OnItemClickListener
) : RecyclerView.Adapter<WordsListAdapter.WordViewHolder>() {

    private var wordsList: MutableList<Word> = mutableListOf()

    interface OnItemClickListener {
        fun onItemClick(item: Int) //todo change to the word model
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): WordViewHolder
            = WordViewHolder(parent)

    override fun getItemCount(): Int = wordsList.size

    override fun onBindViewHolder(holder: WordViewHolder, position: Int) {
        val word = wordsList[position]

        holder.word.text = word.word
        holder.definition.text = word.definition
        holder.likesCount.text = word.thumbsUp.toString()
        holder.unlikesCount.text = word.thumbsDown.toString()
    }

    fun addWords(words: List<Word>) {
        wordsList.clear()
        wordsList.addAll(words)
    }

    fun clear() {
        wordsList.clear()
    }

    class WordViewHolder constructor(parent: ViewGroup) : RecyclerView.ViewHolder(
        LayoutInflater.from(parent.context)
            .inflate(R.layout.item_words_list, parent, false)
    ) {
        val likesCount: TextView = itemView.thumbsUpCountTextView
        val unlikesCount: TextView = itemView.thumbsDownCountTextView
        val word: TextView = itemView.wordTextView
        val definition: TextView = itemView.definitionTextView
    }
}