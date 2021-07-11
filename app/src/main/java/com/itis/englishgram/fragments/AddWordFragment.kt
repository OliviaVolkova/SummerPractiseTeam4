package com.itis.englishgram.fragments

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.google.android.material.snackbar.Snackbar
import com.itis.englishgram.R
import com.itis.englishgram.extensions.hideKeyboard
import com.itis.englishgram.models.Word
import com.itis.englishgram.models.WordsLists

class AddWordFragment: Fragment(R.layout.fragment_add_new_word) {

    lateinit var spellingText : TextView
    lateinit var definitionText : TextView
    lateinit var addButton : Button

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        spellingText = view.findViewById(R.id.et_spelling)
        definitionText = view.findViewById(R.id.et_definition)
        addButton = view.findViewById(R.id.add_word_button)

        addButton.setOnClickListener()
        {
            val spelling = spellingText.text.toString()
            val definition = definitionText.text.toString()

            val word = Word(spelling, definition)

            WordsLists.vocabularyWordList.add(word)

            WordsLists.addWordToUnknown(word)

            showMessage("word \"$spelling\" was added")
            view.hideKeyboard(requireContext())
            findNavController().navigate(R.id.action_addWordFragment_to_vocabularyFragment)
        }
    }

    private fun showMessage(message: String)
    {
        activity?.findViewById<View>(android.R.id.content)?.also {
            Snackbar.make(
                it,
                message,
                Snackbar.LENGTH_LONG
            ).show()
        }
    }

}
