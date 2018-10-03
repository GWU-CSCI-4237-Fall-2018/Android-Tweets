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

    private val PREF_FILENAME = "android-tweets"

    private val PREF_SAVED_USERNAME = "SAVED_USERNAME"

    private lateinit var usernameEditText: EditText

    private lateinit var passwordEditText: EditText

    private lateinit var loginButton: Button

    private lateinit var progressBar: ProgressBar

    /**
     * Called when the Activity is being rendered for the first time, but before anything is
     * shown to the user.
     */
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        // Get a SharedPreferences object, creating the file if it doesn't exist
        val preferences = getSharedPreferences(PREF_FILENAME, Context.MODE_PRIVATE)

        usernameEditText = findViewById(R.id.username)
        passwordEditText = findViewById(R.id.password)
        loginButton = findViewById(R.id.login)
        progressBar = findViewById(R.id.progressBar)

        usernameEditText.addTextChangedListener(textWatcher)
        passwordEditText.addTextChangedListener(textWatcher)

        // Recall the saved username (or default to empty string)
        val savedUsername: String = preferences.getString(PREF_SAVED_USERNAME, "")
        usernameEditText.setText(savedUsername)

        loginButton.setOnClickListener {
            // Save the inputted username (usually would be controlled by a Switch)
            val username = usernameEditText.text.toString()
            preferences.edit().putString(PREF_SAVED_USERNAME, username).apply()

            // Start the ChooseLocationActivity, sending it the inputted username
            val intent = Intent(this, ChooseLocationActivity::class.java)
            intent.putExtra(ChooseLocationActivity.INTENT_KEY_USERNAME, username)
            startActivity(intent)
        }
    }

    /**
     * A [TextWatcher] is set on an [EditText] using [EditText.addTextChangedListener] which
     * receives callbacks as the user types in the [EditText].
     */
    private val textWatcher = object : TextWatcher {
        override fun afterTextChanged(s: Editable) {}

        override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {}

        override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {
            // Only enable the Login button if both the username & password have been inputted
            val usernameText: String = usernameEditText.text.toString()
            val passwordText: String = passwordEditText.text.toString()
            loginButton.isEnabled = usernameText.isNotEmpty() && passwordText.isNotEmpty()
        }
    }
}
