package com.itis.englishgram

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.itis.englishgram.models.WordsLists
import com.itis.englishgram.models.fileWriterReader

class BottomSingleActivity : AppCompatActivity() {

    private lateinit var controller: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        supportActionBar?.hide()
        setContentView(R.layout.activity_bottom_single)
        controller = (supportFragmentManager.findFragmentById(R.id.host_fragment) as NavHostFragment).navController

        val bottomNavigationView = findViewById<BottomNavigationView>(R.id.bnv_main)

        bottomNavigationView.setupWithNavController(controller)

        WordsLists.addExampleWords()

        fileWriterReader.init(filesDir)
    }

    override fun onSupportNavigateUp(): Boolean = controller.navigateUp()
}
