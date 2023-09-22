package com.rey.bunkkaro

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.rey.bunkkaro.databinding.ActivityContactMeBinding

class ContactMeActivity : AppCompatActivity() {
    private lateinit var binding :ActivityContactMeBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityContactMeBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportActionBar?.title = getString(R.string.contact_me)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        binding.webView.loadUrl("https://github.com/Shreyassp002")
    }

    override fun onSupportNavigateUp(): Boolean {
        finish()
        return true
    }
    @Deprecated("Deprecated in Java")
    override fun onBackPressed() {
        if (binding.webView.canGoBack())
            binding.webView.goBack()
        else
            finish()
    }
}