package com.itis.englishgram.fragments

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.itis.englishgram.R
import com.itis.englishgram.models.Word
import com.itis.englishgram.models.WordsLists
import kotlin.math.min
import kotlin.random.Random

class TrainingFragment : Fragment(R.layout.fragment_training) {

    private lateinit var wordView : TextView
    private lateinit var definitionViews : Array<TextView>
    private lateinit var rightAnswerText : TextView
    private lateinit var wrongAnswerText : TextView
    private lateinit var nextButton: Button

    private val random = Random

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        wordView = view.findViewById(R.id.wordView)
        definitionViews = arrayOf(
            view.findViewById(R.id.definitionView0),
            view.findViewById(R.id.definitionView1),
            view.findViewById(R.id.definitionView2),
            view.findViewById(R.id.definitionView3)
        )
        rightAnswerText = view.findViewById(R.id.right_answer_text)
        wrongAnswerText = view.findViewById(R.id.wrong_answer_text)
        nextButton = view.findViewById(R.id.new_word_button)

        initListeners()

        takeNewWord()
    }

    private lateinit var currentWord : Word
    private var rightIndex : Int = 0

    private fun takeNewWord()
    {
        if(WordsLists.learningSize()==0)
        {
            rightIndex=-1
            wordView.text = getString(R.string.out_of_words)
            definitionViews[0].text = getString(R.string.empty_learning_list)
            for(i in 1..3)
                definitionViews[i].visibility = View.INVISIBLE
        }
        else
        {
            currentWord = WordsLists.takeRandomLearning()
            wordView.text = currentWord.spelling
            rightIndex = random.nextInt(min(4, WordsLists.sizesSum()))
            definitionViews[rightIndex].text = currentWord.definition
            for (i in 0..min(3, WordsLists.sizesSum() - 1))
            {
                if (i != rightIndex)
                {
                    var definition = WordsLists.takeRandom().definition
                    while (definition == currentWord.definition)
                        definition = WordsLists.takeRandom().definition
                    definitionViews[i].text = definition
                }
                definitionViews[i].visibility = View.VISIBLE
            }
            for(i in min(3,WordsLists.sizesSum()-1)+1..3)
                definitionViews[i].visibility = View.INVISIBLE
        }
    }

    private fun initListeners()
    {
        for (i in 0..min(3,WordsLists.sizesSum()-1))
            definitionViews[i].setOnClickListener{
                if(i==rightIndex)
                    rightAnswer()
                else
                    wrongAnswer()
            }

        nextButton.setOnClickListener{
            rightAnswerText.visibility = View.INVISIBLE
            wrongAnswerText.visibility = View.INVISIBLE
            takeNewWord()
            nextButton.visibility = View.INVISIBLE
        }
    }

    private fun rightAnswer(){
        WordsLists.moveWordFromLearningToKnown(currentWord)
        rightAnswerText.visibility = View.VISIBLE
        nextButton.visibility = View.VISIBLE
    }

    private fun wrongAnswer(){
        wrongAnswerText.visibility = View.VISIBLE
        nextButton.visibility = View.VISIBLE
    }
}
