package com.itis.englishgram.adapter

import androidx.recyclerview.widget.RecyclerView
import com.itis.englishgram.databinding.ItemWordBinding
import com.itis.englishgram.models.Word

class WordHolder(
    private val binding: ItemWordBinding
    //private val manager: RequestManager,
    //private val action: (City) -> Unit
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(name: Word) {
            with(binding) {
                tvSpelling.text = name.spelling
                tvDefinition.text = name.definition
            }
        }
}