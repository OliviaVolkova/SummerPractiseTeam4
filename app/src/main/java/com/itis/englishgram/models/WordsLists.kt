package com.itis.englishgram.models

import java.lang.Exception
import kotlin.random.Random


//тут система слов для логики. Не для отображения в Vocabulary (там другая система).
class WordsLists {
    companion object
    {
        private val unknownList  = ArrayList<Word>()
        private val learningList = ArrayList<Word>()
        private val knownList = ArrayList<Word>()
        private val random = Random

        fun moveWordFromUnknownToLearning(word: Word)
        {
            if(unknownList.contains(word)) {
                learningList.add(word)
                unknownList.remove(word)
            }
        }
        fun moveWordFromLearningToKnown(word: Word)
        {
            if(learningList.contains(word)) {
                knownList.add(word)
                learningList.remove(word)
            }
        }
        fun moveFromUnknownToKnown(word: Word)
        {
            if(unknownList.contains(word)) {
                knownList.add(word)
                unknownList.remove(word)
            }
        }

        fun getList(collection: Int) : ArrayList<Word> //капец какой unsafe. Это нельзя использовать, но мне лень чета придумывать
        {
            when(collection)
            {
                0 -> return unknownList
                1 -> return learningList
                2 -> return knownList
            }
            throw Exception("Я ЖЕ ТЕБЕ СКАЗАЛ НЕЛЬЗЯ ИСПОЛЬЗОВАТЬ ЭТО")
        }

        fun unknownSize(): Int{
            return unknownList.size
        }
        fun learningSize(): Int{
            return learningList.size
        }
        fun knownSize() : Int{
            return knownList.size
        }
        fun sizesSum(): Int {
            return unknownSize() + learningSize() + knownSize()
        }

        fun takeRandomUnknown() : Word
        {
            if(unknownSize()>0) {
                val index = random.nextInt(unknownSize())
                return unknownList[index]
            }
            else
                return Word("", "")
        }
        fun takeRandomLearning() : Word
        {
            return if(learningSize() >0) {
                val index = random.nextInt(learningSize())
                learningList[index]
            } else
                Word("", "")
        }

        fun takeRandom() : Word
        {
            var index = random.nextInt(sizesSum())
            if(index < unknownSize())
                return unknownList[index]
            index -= unknownSize()
            if(index < learningSize())
                return learningList[index]
            index -= learningSize()
            return knownList[index]
        }


        fun addWordToUnknown(word : Word)
        {
            unknownList.add(word)
        }
        fun addWordToUnknown(words : ArrayList<Word>)
        {
            unknownList.addAll(words)
        }

        fun addWordToLeaning(word : Word)
        {
            learningList.add(word)
        }
        fun addWordToLearning(words : ArrayList<Word>)
        {
            learningList.addAll(words)
        }

        fun addWordToKnown(word : Word)
        {
            knownList.add(word)
        }
        fun addWordToKnown(words : ArrayList<Word>)
        {
            knownList.addAll(words)
        }

        fun addExampleWords()
        {
            //https://www.vocabulary.com/lists/52473
            val exampleWords = arrayListOf<Word>(
                Word("chair", "a seat for one person that has a back, usually four legs, and sometimes two arms"),
                Word("pen", "a long, thin object used for writing or drawing with ink"),
                Word("consider", "deem to be"),
                Word("minute", "infinitely or immeasurably small"),
                Word("accord", "concurrence of opinion"),
                Word("evident", "clearly revealed to the mind or the senses or judgment"),
                Word("practice", "a customary way of operation or behavior"),
                Word("intend", "have in mind as a purpose"),
                Word("concern", "something that interests you because it is important")
            )
            addWordToUnknown(exampleWords)
        }
    }
}
