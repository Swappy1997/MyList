package com.example.mylist

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.mylist.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var notificationAdapter: NotificationAdapterAlternative

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)


        binding.login.isEnabled = false
        binding.login.setOnClickListener {
            val username = binding.mobile.text.toString()
            val password = binding.pass.text.toString()

            if (isValidCredentials(username, password)) {
                showToast("Login successful")
                setupRecyclerView()
            } else {
                showToast("Invalid credentials")
            }
        }

        binding.mobile.addTextChangedListener(loginTextWatcher)
        binding.pass.addTextChangedListener(loginTextWatcher)
    }

    private val loginTextWatcher = object : TextWatcher {
        override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {

        }

        override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
            // Not needed in this case
        }

        override fun afterTextChanged(s: Editable?) {
            // Enable the login button only if both fields are non-empty
            binding.login.isEnabled =
                binding.mobile.text.isNotEmpty() && binding.pass.text.isNotEmpty()
        }
    }

    private fun setupRecyclerView() {
        binding.recycleview.layoutManager = LinearLayoutManager(this)
        notificationAdapter = NotificationAdapterAlternative(generateNotificationItems()) { item ->
            showToast("Clicked: $item")
        }

        binding.recycleview.adapter = notificationAdapter
    }

    private fun generateNotificationItems(): List<String> {
        val notificationItems = mutableListOf<String>()
        for (i in 1..10) {
            notificationItems.add("Item $i")
        }
        return notificationItems
    }

    private fun isValidCredentials(username: String, password: String): Boolean {

        return username.isNotEmpty() && password.isNotEmpty()
    }


    private fun showToast(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }
}