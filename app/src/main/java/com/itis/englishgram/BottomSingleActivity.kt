package com.itis.englishgram

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

class BottomSingleActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        supportActionBar?.hide()
        setContentView(R.layout.activity_bottom_single)
        //
    }
}