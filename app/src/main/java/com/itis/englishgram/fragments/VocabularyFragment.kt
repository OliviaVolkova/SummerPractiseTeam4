package com.itis.englishgram.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.core.view.get
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.itis.englishgram.R
import com.itis.englishgram.adapter.WordAdapter
import com.itis.englishgram.databinding.FragmentVocabularyBinding
import com.itis.englishgram.models.Word

class VocabularyFragment : Fragment() {
    private var binding: FragmentVocabularyBinding? = null
    private var adapter: WordAdapter? = null


    private var words = ArrayList<Word>()
        /*arrayListOf(
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
    )*/


    var btnNewWord: Button? = null

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

        btnNewWord = view.findViewById(R.id.btn_add_word)

        btnNewWord?.setOnClickListener {
            findNavController().navigate(R.id.action_vocabularyFragment_to_addWordFragment)
        }


        adapter = WordAdapter(words)

        binding?.rvWords?.adapter = adapter
        /*
        adapter?.AddItem(Word("test", "aaaaaa"))
        adapter?.AddItem(Word("test", "aaaaaa"))
        adapter?.AddItem(Word("test", "aaaaaa"))
        adapter?.AddItem(Word("test", "aaaaaa"))*/

    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }
}
