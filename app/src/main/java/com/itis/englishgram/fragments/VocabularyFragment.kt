package com.itis.englishgram.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.itis.englishgram.R
import com.itis.englishgram.adapter.WordAdapter
import com.itis.englishgram.databinding.FragmentVocabularyBinding
import com.itis.englishgram.models.Word

class VocabularyFragment : Fragment() {
    private var binding: FragmentVocabularyBinding? = null
    private var adapter: WordAdapter? = null


    private var words = arrayListOf(
        Word("wampish", "to wave about or flop to and fro"),
        Word("girandole", "a rotating and radiating firework."),
        Word("cunctation", "lateness; delay."),
        Word("shivoo", "a boisterous party or celebration."),
        Word("estivate", "to spend the summer, as at a specific place or in a certain activity."),Word("wampish", "to wave about or flop to and fro"),
        Word("girandole", "a rotating and radiating firework."),
        Word("cunctation", "lateness; delay."),
        Word("shivoo", "a boisterous party or celebration."),
        Word("estivate", "to spend the summer, as at a specific place or in a certain activity."),Word("wampish", "to wave about or flop to and fro"),
        Word("girandole", "a rotating and radiating firework."),
        Word("cunctation", "lateness; delay."),
        Word("shivoo", "a boisterous party or celebration."),
        Word("estivate", "to spend the summer, as at a specific place or in a certain activity."),Word("wampish", "to wave about or flop to and fro"),
        Word("girandole", "a rotating and radiating firework."),
        Word("cunctation", "lateness; delay."),
        Word("shivoo", "a boisterous party or celebration."),
        Word("estivate", "to spend the summer, as at a specific place or in a certain activity."),Word("wampish", "to wave about or flop to and fro"),
        Word("girandole", "a rotating and radiating firework."),
        Word("cunctation", "lateness; delay."),
        Word("shivoo", "a boisterous party or celebration."),
        Word("estivate", "to spend the summer, as at a specific place or in a certain activity."),Word("wampish", "to wave about or flop to and fro"),
        Word("girandole", "a rotating and radiating firework."),
        Word("cunctation", "lateness; delay."),
        Word("shivoo", "a boisterous party or celebration."),
        Word("estivate", "to spend the summer, as at a specific place or in a certain activity.to spend the summer, as at a specific place or in a certain activity.to spend the summer, as at a specific place or in a certain activity.to spend the summer, as at a specific place or in a certain activity.to spend the summer, as at a specific place or in a certain activity.to spend the summer, as at a specific place or in a certain activity.to spend the summer, as at a specific place or in a certain activity.to spend the summer, as at a specific place or in a certain activity.to spend the summer, as at a specific place or in a certain activity.to spend the summer, as at a specific place or in a certain activity.to spend the summer, as at a specific place or in a certain activity.to spend the summer, as at a specific place or in a certain activity.to spend the summer, as at a specific place or in a certain activity.to spend the summer, as at a specific place or in a certain activity.to spend the summer, as at a specific place or in a certain activity.to spend the summer, as at a specific place or in a certain activity.to spend the summer, as at a specific place or in a certain activity.to spend the summer, as at a specific place or in a certain activity.to spend the summer, as at a specific place or in a certain activity.to spend the summer, as at a specific place or in a certain activity."),
    )


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentVocabularyBinding.inflate(inflater, container, false)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        adapter = WordAdapter(words)
        binding?.rvWords?.adapter = adapter
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }
}