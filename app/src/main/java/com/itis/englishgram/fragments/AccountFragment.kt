package com.itis.englishgram.fragments

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.google.firebase.auth.FirebaseAuth
import com.itis.englishgram.BottomSingleActivity
import com.itis.englishgram.LoginActivity
import com.itis.englishgram.R

class AccountFragment: Fragment(R.layout.fragment_account) {

    private lateinit var mAuth: FirebaseAuth

    private var btnLogout : Button?=null
    private var btnToLearn : Button?=null
    private var btnIKnow : Button?=null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        mAuth= FirebaseAuth.getInstance()

        btnFindView()
        btnActivity()
    }

    fun btnActivity(){

        btnLogout?.setOnClickListener{

            mAuth.signOut()

            val intent = Intent(activity, LoginActivity::class.java)
            startActivity(intent)
        }

        btnToLearn?.setOnClickListener{
            findNavController().navigate(R.id.action_accountFragment_to_wordsToStudy)
        }

        btnIKnow?.setOnClickListener{
            findNavController().navigate(R.id.action_accountFragment_to_wordsWhichIKnow)
        }
    }

    fun btnFindView(){

        btnLogout=view?.findViewById(R.id.btn_log_out)
        btnToLearn=view?.findViewById(R.id.btn_toLearn)
        btnIKnow=view?.findViewById(R.id.btn_IKnow)
    }
}