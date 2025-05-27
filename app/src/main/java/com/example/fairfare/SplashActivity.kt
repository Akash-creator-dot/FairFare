package com.example.fairfare

import android.content.Intent
import android.os.AsyncTask
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class SplashActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_splash)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
       startHeavytask()
    }

    private fun startHeavytask() {
       longExpression().execute()
    }
    private open inner class longExpression : AsyncTask<String?, Void?, String?>() {
        private var isLoaded = false

        override fun doInBackground(vararg p0: String?): String? {
            // Simulate waiting for something to load
            while (!isLoaded) {
                try {
                    // You can check for some real loading condition here
                    Thread.sleep(1000) // Simulate delay
                    // Fake loading condition after 5 seconds
                    isLoaded = System.currentTimeMillis() % 3 == 0L // Simulate some load
                } catch (e: Exception) {
                    Thread.interrupted()
                }
            }
            return "loaded"
        }

        override fun onPostExecute(result: String?) {
            super.onPostExecute(result)
            val intent = Intent(this@SplashActivity, LoginActivity::class.java)
            startActivity(intent)
            finish()
        }
    }
}