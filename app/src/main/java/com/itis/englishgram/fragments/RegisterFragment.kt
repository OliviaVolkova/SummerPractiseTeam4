package com.itis.englishgram.fragments

import android.os.Bundle
import android.text.TextUtils
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import com.google.android.material.textfield.TextInputLayout
import com.itis.englishgram.R
import com.itis.englishgram.database.UserAccount
import com.itis.englishgram.database.UserViewModel

class RegisterFragment:Fragment(R.layout.fragment_register) {

    private var etEmail: EditText? = null
    private var tiEmail: TextInputLayout? = null
    private var etPassword: EditText? = null
    private var tiPassword: TextInputLayout? = null
    private var btnRegister: Button? = null

    private lateinit var mUserViewModel: UserViewModel

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        findView1(view)
        mUserViewModel=ViewModelProvider(this).get(UserViewModel::class.java)
        btnRegister?.setOnClickListener{
            inputDataToDatabase()
        }

    }

    private fun inputDataToDatabase() {
        val email=etEmail?.text.toString()
        val password=etPassword?.text.toString()

        if(inputCheck(email,password)){
            val user=UserAccount(0,email,password)
            mUserViewModel.addUser(user)
            Toast.makeText(requireContext(),"you are user now",Toast.LENGTH_LONG).show()

            findNavController().navigate(R.id.action_registerFragment_to_loginFragment)

        }else{
            Toast.makeText(requireContext(),"Wrong",Toast.LENGTH_LONG).show()
        }

    }

    private fun inputCheck(email:String,password:String):Boolean
    {
        return !(TextUtils.isEmpty(email)||TextUtils.isEmpty(password))

    }

    private fun findView1(view: View)
    {
        etEmail = view.findViewById(R.id.et_email)
        tiEmail = view.findViewById(R.id.ti_email)
        etPassword = view.findViewById(R.id.et_password)
        tiPassword = view.findViewById(R.id.ti_password)
        btnRegister = view.findViewById(R.id.btn_register)
    }
}
