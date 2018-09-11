package com.gwu.android.androidtweets

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.ProgressBar

class LoginActivity : AppCompatActivity() {

    private lateinit var usernameEditText: EditText

    private lateinit var passwordEditText: EditText

    private lateinit var loginButton: Button

    private lateinit var progressBar: ProgressBar

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        usernameEditText = findViewById(R.id.username)
        passwordEditText = findViewById(R.id.password)
        loginButton = findViewById(R.id.login)
        progressBar = findViewById(R.id.progressBar)

        usernameEditText.addTextChangedListener(textWatcher)
        passwordEditText.addTextChangedListener(textWatcher)

        loginButton.setOnClickListener { progressBar.visibility = View.VISIBLE }
    }

    private val textWatcher = object : TextWatcher {
        override fun afterTextChanged(s: Editable) {}

        override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {}

        override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {
            val usernameText: String = usernameEditText.text.toString()
            val passwordText: String = passwordEditText.text.toString()
            loginButton.isEnabled = usernameText.isNotEmpty() && passwordText.isNotEmpty()
        }
    }
}
