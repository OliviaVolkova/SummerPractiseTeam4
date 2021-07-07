package com.itis.englishgram.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.itis.englishgram.databinding.ItemWordBinding
import com.itis.englishgram.models.Word

class WordAdapter (
    var list: MutableList<Word> = mutableListOf<Word>()
    ) : RecyclerView.Adapter<WordHolder>()
{
        override fun onCreateViewHolder(
            parent: ViewGroup,
            viewType: Int
        ): WordHolder = WordHolder(ItemWordBinding.inflate(LayoutInflater.from(parent.context), parent, false))

        override fun onBindViewHolder(holder: WordHolder, position: Int) = holder.bind(list[position])

        override fun getItemCount(): Int = list.size

    fun getItem(index: Int): Word
    {
        return list[index]
    }

    fun addItem(item: Word)
    {
        list.add(item)
    }
}
