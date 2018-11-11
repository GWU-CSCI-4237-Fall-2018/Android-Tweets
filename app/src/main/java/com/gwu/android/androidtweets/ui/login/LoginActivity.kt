package com.gwu.android.androidtweets.ui.login

import android.content.Context
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.widget.Button
import android.widget.EditText
import android.widget.ProgressBar
import android.widget.Toast
import com.crashlytics.android.Crashlytics
import com.google.firebase.analytics.FirebaseAnalytics
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException
import com.gwu.android.androidtweets.ui.maps.ChooseLocationActivity
import com.gwu.android.androidtweets.R

class LoginActivity : AppCompatActivity() {

    private val PREF_FILENAME = "android-tweets"

    private val PREF_SAVED_USERNAME = "SAVED_USERNAME"

    private lateinit var usernameEditText: EditText

    private lateinit var passwordEditText: EditText

    private lateinit var loginButton: Button

    private lateinit var signUpButton: Button

    private lateinit var progressBar: ProgressBar

    private lateinit var firebaseAuth: FirebaseAuth

    private lateinit var firebaseAnalytics: FirebaseAnalytics

    /**
     * Called when the Activity is being rendered for the first time, but before anything is
     * shown to the user.
     */
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        firebaseAnalytics = FirebaseAnalytics.getInstance(this)
        firebaseAuth = FirebaseAuth.getInstance()

        // Get a SharedPreferences object, creating the file if it doesn't exist
        val preferences = getSharedPreferences(PREF_FILENAME, Context.MODE_PRIVATE)

        usernameEditText = findViewById(R.id.username)
        passwordEditText = findViewById(R.id.password)
        loginButton = findViewById(R.id.login)
        signUpButton = findViewById(R.id.signUp)
        progressBar = findViewById(R.id.progressBar)

        usernameEditText.addTextChangedListener(textWatcher)
        passwordEditText.addTextChangedListener(textWatcher)

        // Recall the saved username (or default to empty string)
        val savedUsername: String = preferences.getString(PREF_SAVED_USERNAME, "")
        usernameEditText.setText(savedUsername)

        loginButton.setOnClickListener {
            // Save the inputted username (usually would be controlled by a Switch)
            val username = usernameEditText.text.toString()
            val password = passwordEditText.text.toString()

            firebaseAuth.signInWithEmailAndPassword(username, password).addOnCompleteListener { task ->
                if (task.isSuccessful) {

                    firebaseAnalytics.logEvent("login_success", null)

                    val user = firebaseAuth.currentUser

                    if (user != null) {
                        Toast.makeText(this, "Logged in as ${user.email}!", Toast.LENGTH_SHORT).show()
                    }

                    preferences.edit().putString(PREF_SAVED_USERNAME, username).apply()

                    // Start the ChooseLocationActivity, sending it the inputted username
                    val intent = Intent(this, ChooseLocationActivity::class.java)
                    intent.putExtra(ChooseLocationActivity.INTENT_KEY_USERNAME, username)
                    startActivity(intent)
                } else {
                    val exception = task.exception
                    val errorType = if (exception is FirebaseAuthInvalidCredentialsException)
                        "invalid credentials" else "network connection"

                    // Acts similar to an Intent
                    val bundle = Bundle()
                    bundle.putString("error_type", errorType)

                    firebaseAnalytics.logEvent("login_failed", bundle)

                    Toast.makeText(this, "Login failed: $exception", Toast.LENGTH_SHORT).show()
                }
            }

        }

        signUpButton.setOnClickListener {
            val username = usernameEditText.text.toString()
            val password = passwordEditText.text.toString()

            firebaseAuth.createUserWithEmailAndPassword(username, password).addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    val user = firebaseAuth.currentUser
                    if (user != null) {
                        Toast.makeText(this, "Account created for ${user.email}!", Toast.LENGTH_SHORT).show()
                    }
                } else {
                    val exception = task.exception
                    Toast.makeText(this, "Account creation failed! $exception", Toast.LENGTH_SHORT).show()
                }
            }
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
