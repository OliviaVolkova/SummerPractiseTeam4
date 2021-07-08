package com.itis.englishgram.models

import android.util.Log
import java.lang.StringBuilder

class wordsInitialiser //эта херня ломает все, если сломать файлы
{
    companion object
    {
        fun loadWords()
        {
            for(i in 0..2)
            {
                val text = fileWriterReader.read(i)
                val array = text.split('\n')
                val words = ArrayList<Word>()
                for(j in array.indices)
                {
                    val temp = array[j].split(';')
                    //Log.i("aaaaaaa",array[i])
                    if(temp.size==2)
                    words.add(Word(temp[0],temp[1]))
                }
                when(i)
                {
                    0 -> WordsLists.addWordToUnknown(words)
                    1 -> WordsLists.addWordToLearning(words)
                    2 -> WordsLists.addWordToKnown(words)
                }
            }
        }

        fun saveWords()
        {
            for(i in 0..2)
            {
                val r = WordsLists.getList(i)
                val f = StringBuilder()
                for(j in 0 until r.size)
                    f.append(r[j].spelling+";"+r[j].definition+"\n")
                fileWriterReader.write(f.toString(),i)
            }
        }
    }
}
