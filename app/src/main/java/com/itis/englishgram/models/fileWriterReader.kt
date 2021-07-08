package com.itis.englishgram.models

import android.app.AlertDialog
import android.content.Context
import android.util.Log
import com.itis.englishgram.BottomSingleActivity
import java.io.File
import java.io.FileInputStream
import java.io.FileOutputStream
import java.lang.Exception


//функции write и read принимают collection : Int
//collection = :
//1. файл пишется - читается с unknown.txt
//2. - learning.txt
//3. - known.txt


class fileWriterReader
{
    companion object
    {
        private lateinit var path : File
        private lateinit var letDirectory : File

        private lateinit var files : Array<File?>

        fun init(_path : File)
        {
            path = _path

            letDirectory = File(path,"words")
            letDirectory.mkdirs()

            files = Array(3) {null}

            files[0] = File(letDirectory, "unknown.txt")
            files[1] = File(letDirectory, "learning.txt")
            files[2] = File(letDirectory, "known.txt")
        }

        fun write(text : String, collection: Int)
        {
            FileOutputStream(files[collection]).use{
                it.write(text.toByteArray())
            }
        }

        fun read(collection: Int) : String
        {
            //Log.i("asdfgdfjshdfsha",collection.toString())
            var g = ""
            try
            {
                g = FileInputStream(files[collection]).bufferedReader().use {
                    it.readText()
                }
            }
            catch(e : Exception){
                Log.i("ERROR", "файл не читается. Ошибка: $e")
            }
            return g
        }
    }
}
