package com.itis.englishgram.fragments

import android.content.Intent
import android.os.Bundle
import android.text.TextUtils
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.google.android.material.snackbar.Snackbar
import com.google.android.material.textfield.TextInputLayout
import com.google.firebase.auth.FirebaseAuth
import com.itis.englishgram.BottomSingleActivity
import com.itis.englishgram.LoginActivity
import com.itis.englishgram.R
import com.itis.englishgram.extensions.hideKeyboard

class LoginFragment: Fragment(R.layout.fragment_login) {

    private lateinit var mAuth:FirebaseAuth

    private val users = hashMapOf<String, String>("test" to "12345", "1" to "1")

    private var etEmail: EditText? = null
    private var tiEmail: TextInputLayout? = null
    private var etPassword: EditText? = null
    private var tiPassword: TextInputLayout? = null
    private var btnLogin: Button? = null
    private var btnRegister: Button? = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        findView(view)
        mAuth= FirebaseAuth.getInstance()
        btnLogin?.setOnClickListener{
            loginUser()
        }
        btnRegister?.setOnClickListener{
            findNavController().navigate(R.id.action_loginFragment_to_registerFragment)
        }

        //initListeners()
    }

    private fun loginUser() {
        val email=etEmail?.text.toString()
        val password=etPassword?.text.toString()
        if (inputCheck(email,password)){
            mAuth.signInWithEmailAndPassword(email,password).addOnCompleteListener {
                task ->
                if(task.isSuccessful){
                    val intent = Intent(activity, BottomSingleActivity::class.java) //mb broken
                    intent.putExtra("EXTRA_EMAIL", email)
                    intent.putExtra("EXTRA_PASS", password)
                    startActivity(intent)
                }
                else{
                    Toast.makeText(requireContext(),"Ошибка: "+task.exception!!.message.toString()
                        ,Toast.LENGTH_LONG)
                        .show()
                }
            }
        }
        else {
            Toast.makeText(requireContext(),"Fill all empty fields please", Toast.LENGTH_LONG).show()
        }
    }

    //this fum for loginUser
    private fun inputCheck(email:String,password:String):Boolean
    {
        return !(TextUtils.isEmpty(email)|| TextUtils.isEmpty(password))
    }

    // Я боюсь удалять эту функцию, так что пусть пока будет, потому что я пока сам не особо понимаю как работате бд
    // при регистрации
    private fun initListeners()
    {
        btnLogin?.setOnClickListener{
            val email = etEmail?.text?.toString() ?: ""
            val password = etPassword?.text?.toString() ?: ""

            if(email.isEmpty())
            {
                tiEmail?.error = getString(R.string.empty_field)
                return@setOnClickListener
            }

            if(password.isEmpty())
            {
                tiPassword?.error = getString(R.string.empty_field)
                return@setOnClickListener
            }


            var passFromBase: String? = users[email]
            var message = when{
                passFromBase == null -> "User not found"
                password == passFromBase -> {
                    val intent = Intent(activity, BottomSingleActivity::class.java) //mb broken
                    intent.putExtra("EXTRA_EMAIL", email)
                    intent.putExtra("EXTRA_PASS", password)
                    startActivity(intent)
                    "Nice"
                }
                else -> "Correct password: $passFromBase"
            }
            etEmail?.hideKeyboard(requireContext())
            showMessage(message)
        }

        btnRegister?.setOnClickListener{
            findNavController().navigate(R.id.action_loginFragment_to_registerFragment)
        }

        etEmail?.addTextChangedListener {
            tiEmail?.isErrorEnabled = false
            tiEmail?.error = null
        }

        etPassword?.addTextChangedListener {
            tiPassword?.isErrorEnabled = false
            tiPassword?.error = null
        }
    }

    //это я хз нужно или нет
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

    private fun findView(view: View)
    {
        etEmail = view.findViewById(R.id.et_email)
        tiEmail = view.findViewById(R.id.ti_email)
        etPassword = view.findViewById(R.id.et_password)
        tiPassword = view.findViewById(R.id.ti_password)
        btnLogin = view.findViewById(R.id.btn_login)
        btnRegister = view.findViewById(R.id.btn_register)
    }
}