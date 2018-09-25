package com.gwu.android.androidtweets

import android.content.Context
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
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

        // Get a SharedPreferences object
        val preferences = getSharedPreferences("android-tweets", Context.MODE_PRIVATE)

        usernameEditText = findViewById(R.id.username)
        passwordEditText = findViewById(R.id.password)
        loginButton = findViewById(R.id.login)
        progressBar = findViewById(R.id.progressBar)

        usernameEditText.addTextChangedListener(textWatcher)
        passwordEditText.addTextChangedListener(textWatcher)

        // Recall the saved username (or default to empty string)
        val savedUsername: String = preferences.getString("SAVED_USERNAME", "")
        usernameEditText.setText(savedUsername)

        loginButton.setOnClickListener {
            // Save the inputted username (usually would be controlled by a Switch)
            val username = usernameEditText.text.toString()
            preferences.edit().putString("SAVED_USERNAME", username).apply()

            val intent = Intent(this, TweetsActivity::class.java).apply {
                putExtra(TweetsActivity.INTENT_KEY_LOCATION, "Washington D.C.")
            }
            startActivity(intent)
        }
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
