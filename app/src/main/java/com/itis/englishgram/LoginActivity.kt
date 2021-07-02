package com.itis.englishgram

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.core.widget.addTextChangedListener
import com.google.android.material.snackbar.Snackbar
import com.google.android.material.textfield.TextInputLayout
import com.itis.englishgram.extensions.hideKeyboard

class LoginActivity : AppCompatActivity() {
    private val users = hashMapOf<String, String>("test" to "12345")

    private var etEmail: EditText? = null
    private var tiEmail: TextInputLayout? = null
    private var etPassword: EditText? = null
    private var tiPassword: TextInputLayout? = null
    private var btnLogin: Button? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        findView()
        initListeners()
    }


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
                    val intent = Intent(this, BottomSingleActivity::class.java)
                    intent.putExtra("EXTRA_EMAIL", email)
                    intent.putExtra("EXTRA_PASS", password)
                    startActivity(intent)
                    "Nice"
                }
                else -> "Correct password: $passFromBase"
            }
            etEmail?.hideKeyboard(this)
            showMessage(message)
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

    private fun showMessage(message: String)
    {
        Snackbar.make(
            findViewById(android.R.id.content),
            message,
            Snackbar.LENGTH_LONG
        ).show()
    }

    private fun findView()
    {
        etEmail = findViewById(R.id.et_email)
        tiEmail = findViewById(R.id.ti_email)
        etPassword = findViewById(R.id.et_password)
        tiPassword = findViewById(R.id.ti_password)
        btnLogin = findViewById(R.id.btn_login)
    }
}