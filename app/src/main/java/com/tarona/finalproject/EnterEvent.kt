package com.tarona.finalproject

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import android.widget.Toast
import com.tarona.finalproject.databinding.ActivityEnterEventBinding
import com.tarona.finalproject.models.Post
import com.tarona.finalproject.network.RetrofitClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class EnterEvent : AppCompatActivity() {
    private lateinit var binding: ActivityEnterEventBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityEnterEventBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        binding.enterBtn.setOnClickListener {
            if(!binding.title.text.isNullOrBlank()) {
                enterEvent()
            }
        }
    }

    override fun onOptionsItemSelected(participant: MenuItem): Boolean {
        when (participant.itemId) {
            android.R.id.home -> {
                finish()
                return true
            }
        }
        return super.onOptionsItemSelected(participant)
    }

    private fun enterEvent() {
        val post = Post(
            id = "",
            name = binding.title.text.toString(),
            description = "",
        )
        RetrofitClient.apiService.enterEvent(post).enqueue(object: Callback<Post> {
            override fun onResponse(call: Call<Post>, response: Response<Post>) {
                if (response.isSuccessful) {
                    finish()
                    successCreate()
                } else {
                    showError()
                }
            }

            override fun onFailure(call: Call<Post>, t: Throwable) {
                showError()
            }
        })
    }

    private fun showError() {
        Toast.makeText(this, "Failed to create event.", Toast.LENGTH_SHORT).show()
    }

    private fun successCreate() {
        Toast.makeText(this, "Event participated successfully.", Toast.LENGTH_SHORT).show()
    }
}