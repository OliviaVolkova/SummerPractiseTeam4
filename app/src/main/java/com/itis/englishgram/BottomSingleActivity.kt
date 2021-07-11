package com.itis.englishgram

import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.itis.englishgram.models.FileWriterReader
import com.itis.englishgram.models.WordsInitializer
import com.itis.englishgram.models.WordsLists

class BottomSingleActivity : AppCompatActivity() {

    private lateinit var prefs: SharedPreferences
    private lateinit var controller: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        supportActionBar?.hide()
        setContentView(R.layout.activity_bottom_single)
        controller = (supportFragmentManager.findFragmentById(R.id.host_fragment) as NavHostFragment).navController

        val bottomNavigationView = findViewById<BottomNavigationView>(R.id.bnv_main)

        bottomNavigationView.setupWithNavController(controller)

        FileWriterReader.init(filesDir)
        WordsInitializer.loadWords()
        //WordsLists.addExampleWords()
        prefs = getSharedPreferences("com.itis.englishgram", MODE_PRIVATE)
    }

    override fun onResume()
    {
        super.onResume()

        if(prefs.getBoolean("firstRun",true))
        {
            prefs.edit().putBoolean("firstRun",false).apply()

            WordsLists.addExampleWords()
        }
    }
    override fun onDestroy() {
        super.onDestroy()

        WordsInitializer.saveWords()
    }

    override fun onPause() {
        super.onPause()

        WordsInitializer.saveWords()
    }

    override fun onSupportNavigateUp(): Boolean = controller.navigateUp()
}
