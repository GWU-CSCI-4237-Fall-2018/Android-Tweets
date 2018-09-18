package com.gwu.android.androidtweets

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

    fun foo(param1: Int = 0, param2: String = "") {}

    override fun onCreate(savedInstanceState: Bundle?) {
        foo("hi")
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        usernameEditText = findViewById(R.id.username)
        passwordEditText = findViewById(R.id.password)
        loginButton = findViewById(R.id.login)
        progressBar = findViewById(R.id.progressBar)

        usernameEditText.addTextChangedListener(textWatcher)
        passwordEditText.addTextChangedListener(textWatcher)

        loginButton.setOnClickListener {
            val intent: Intent = Intent(this, TweetsActivity::class.java)
            intent.putExtra(TweetsActivity.INTENT_KEY_LOCATION, "Washington D.C.")
//            startActivity(intent)





            val sendTextIntent = Intent().apply {
                action = Intent.ACTION_SEND
                type = "text/plain"
                putExtra(Intent.EXTRA_TEXT, "I'm searching for Tweets near DC!")
            }
            startActivity(sendTextIntent)



//            progressBar.visibility = View.VISIBLE
        }
//        Log.d("LoginActivity", "onCreate")
    }

    override fun onStart() {
        super.onStart()
        Log.d("LoginActivity", "onStart")
    }

    override fun onResume() {
        super.onResume()
        Log.d("LoginActivity", "onResume")
    }

    override fun onPause() {
        super.onPause()
        Log.d("LoginActivity", "onPause")
    }

    override fun onStop() {
        super.onStop()
        Log.d("LoginActivity", "onStop")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d("LoginActivity", "onDestroy")
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
