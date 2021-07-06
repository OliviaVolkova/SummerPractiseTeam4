package com.itis.englishgram.fragments

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.itis.englishgram.R
import com.itis.englishgram.models.Word
import com.itis.englishgram.models.WordsLists

class LearnFragment : Fragment(R.layout.fragment_learn)
{
    lateinit var wordView : TextView
    lateinit var definitionView : TextView

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initListeners()

        wordView = view.findViewById(R.id.wordView)
        definitionView = view.findViewById(R.id.definitionView)

        WordsLists.addExampleWords()

        takeNewWord()
    }

    lateinit var currentWord : Word

    fun takeNewWord()
    {
        currentWord = WordsLists.takeRandomUnknown()
        wordView.text = currentWord.spelling
        definitionView.text = currentWord.definition
    }

    fun initListeners()
    {
        val knowButton = view?.findViewById<Button>(R.id.IKnowButton)
        knowButton?.setOnClickListener(){
            WordsLists.moveFromUnknownToKnown(currentWord)
            takeNewWord()
        }

        val learnButton = view?.findViewById<Button>(R.id.ToLearnButton)
        learnButton?.setOnClickListener(){
            WordsLists.moveWordFromUnknownToLearning(currentWord)
            takeNewWord()
        }

        val skipButton = view?.findViewById<Button>(R.id.SkipButton)
        skipButton?.setOnClickListener(){
            takeNewWord()
        }

    }
}
