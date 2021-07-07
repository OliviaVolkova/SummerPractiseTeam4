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
    private lateinit var wordView : TextView
    private lateinit var definitionView : TextView
    private lateinit var knowButton : Button
    private lateinit var learnButton : Button
    private lateinit var skipButton : Button


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        wordView = view.findViewById(R.id.wordView)
        definitionView = view.findViewById(R.id.definitionView)
        knowButton = view.findViewById<Button>(R.id.IKnowButton)
        learnButton = view.findViewById<Button>(R.id.ToLearnButton)
        skipButton = view.findViewById<Button>(R.id.SkipButton)

        initListeners()

        takeNewWord()
    }

    private lateinit var currentWord : Word
    private var noWords : Boolean = false

    private fun takeNewWord()
    {
        if(WordsLists.unknownSize()==0)
        {
            noWords = true
            wordView.text = getString(R.string.out_of_words)
            definitionView.text = getString(R.string.we_have_no_new_words)
            knowButton.visibility = View.INVISIBLE
            learnButton.visibility = View.INVISIBLE
            skipButton.visibility = View.INVISIBLE
        }
        else
        {
            noWords = false
            currentWord = WordsLists.takeRandomUnknown()
            wordView.text = currentWord.spelling
            definitionView.text = currentWord.definition
        }
    }

    private fun initListeners()
    {
        knowButton.setOnClickListener(){
            WordsLists.moveFromUnknownToKnown(currentWord)
            takeNewWord()
        }

        learnButton.setOnClickListener(){
            WordsLists.moveWordFromUnknownToLearning(currentWord)
            takeNewWord()
        }

        skipButton.setOnClickListener(){
            takeNewWord()
        }

    }
}
