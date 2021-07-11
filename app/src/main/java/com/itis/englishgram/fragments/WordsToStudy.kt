package com.itis.englishgram.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.navigation.fragment.findNavController
import com.itis.englishgram.R
import com.itis.englishgram.adapter.WordAdapter
import com.itis.englishgram.databinding.FragmentWordsToStudyBinding
import com.itis.englishgram.models.Word
import com.itis.englishgram.models.WordsLists


class WordsToStudy : Fragment(R.layout.fragment_words_to_study) {

    private var binding: FragmentWordsToStudyBinding? = null
    private var words = ArrayList<Word>()
    private var adapter: WordAdapter? = null
    private var btnBackToMain: Button? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentWordsToStudyBinding.inflate(inflater, container, false)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        btnBackToMain=view.findViewById(R.id.btn_backToMain)
        btnBackToMain?.setOnClickListener{
            findNavController().navigate(R.id.action_wordsToStudy_to_accountFragment)
        }

        words=WordsLists.unknownList
        adapter = WordAdapter(words)
        binding?.wordsToStudy?.adapter = adapter

    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }

}