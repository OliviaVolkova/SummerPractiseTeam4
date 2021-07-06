package com.itis.englishgram.fragments

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.itis.englishgram.R
import com.itis.englishgram.models.Word
import com.itis.englishgram.models.WordsLists
import org.w3c.dom.Text
import kotlin.math.min
import kotlin.random.Random

class TrainingFragment : Fragment(R.layout.fragment_training) {

    lateinit var wordView : TextView
    lateinit var definitionViews : Array<TextView>
    lateinit var rightAnswerText : TextView
    lateinit var wrongAnswerText : TextView
    lateinit var nextButton: Button

    val random = Random

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        wordView = view.findViewById(R.id.wordView)
        definitionViews = arrayOf(
            view.findViewById(R.id.definitionView0),
            view.findViewById(R.id.definitionView1),
            view.findViewById(R.id.definitionView2),
            view.findViewById(R.id.definitionView3)
        )
        rightAnswerText = view.findViewById(R.id.rightAnswerText)
        wrongAnswerText = view.findViewById(R.id.wrongAnswerText)
        nextButton = view.findViewById(R.id.NextWordButton)

        initListeners()

        takeNewWord()
    }

    lateinit var currentWord : Word
    var rightIndex : Int = 0

    fun takeNewWord()
    {
        currentWord = WordsLists.takeRandomUnknown()
        wordView.text = currentWord.spelling

        rightIndex = random.nextInt(4)
        definitionViews[rightIndex].text = currentWord.definition
        for(i in 0..min(3,WordsLists.sizesSum()-1))
            if(i!=rightIndex)
            {
                var definition = WordsLists.takeRandom().definition
                while (definition == currentWord.definition)
                    definition = WordsLists.takeRandom().definition
                definitionViews[i].text = definition
            }
    }

    fun initListeners()
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

    fun rightAnswer(){
        WordsLists.moveWordFromLearningToKnown(currentWord)
        rightAnswerText.visibility = View.VISIBLE
        nextButton.visibility = View.VISIBLE
    }

    fun wrongAnswer(){
        wrongAnswerText.visibility = View.VISIBLE
        nextButton.visibility = View.VISIBLE
    }
}