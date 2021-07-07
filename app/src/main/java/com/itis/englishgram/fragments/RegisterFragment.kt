package com.itis.englishgram.fragments

import android.app.AlertDialog
import android.content.Intent
import android.os.Bundle
import android.text.TextUtils
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.ProgressBar
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import com.google.android.material.textfield.TextInputLayout
import com.google.firebase.database.DatabaseReference
import com.itis.englishgram.R
import com.itis.englishgram.database.UserAccount
import com.itis.englishgram.database.UserViewModel
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.database.FirebaseDatabase

class RegisterFragment:Fragment(R.layout.fragment_register) {

    private var etEmail: EditText? = null
    private var tiEmail: TextInputLayout? = null
    private var etPassword: EditText? = null
    private var tiPassword: TextInputLayout? = null
    private var btnRegister: Button? = null

    private lateinit var mAuth:FirebaseAuth
    private lateinit var refUsers:DatabaseReference
    private var firebaseUserID: String=" "





    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        findView1(view)
        mAuth= FirebaseAuth.getInstance()

        btnRegister?.setOnClickListener{
            createAccount()
        }

    }

    private fun createAccount() {
        val email=etEmail?.text.toString()
        val password=etPassword?.text.toString()

        if(inputCheck(email,password)){

            mAuth.createUserWithEmailAndPassword(email,password).addOnCompleteListener {
                task ->
                if (task.isSuccessful)
                {
                    firebaseUserID=mAuth.currentUser!!.uid
                    refUsers=FirebaseDatabase.getInstance().reference.child("users").
                            child(firebaseUserID)

                    val userHashMap=HashMap<String,Any>()
                    userHashMap["uid"]=firebaseUserID
                    userHashMap["email"]=email

                    refUsers.updateChildren(userHashMap).addOnCompleteListener {
                        task ->
                        if (task.isSuccessful){
                            Toast.makeText(requireContext(),"Reg was successful",Toast.LENGTH_LONG).show()
                            findNavController().navigate(R.id.action_registerFragment_to_loginFragment)
                        }
                    }
                }
                else
                {
                    Toast.makeText(requireContext(),"Ошибка: "+task.exception!!.message.toString()
                        ,Toast.LENGTH_LONG)
                        .show()
                }
            }


        }else{
            Toast.makeText(requireContext(),"Fill all empty fields please",Toast.LENGTH_LONG).show()
        }

    }

    //this fun for createAccount
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
