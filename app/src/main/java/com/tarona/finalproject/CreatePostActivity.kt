package com.tarona.finalproject

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import android.widget.Toast
import com.tarona.finalproject.databinding.ActivityCreatePostBinding
import com.tarona.finalproject.models.Post
import com.tarona.finalproject.network.RetrofitClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class CreatePostActivity : AppCompatActivity() {
    private lateinit var binding: ActivityCreatePostBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCreatePostBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        binding.save.setOnClickListener {
            if(!binding.title.text.isNullOrBlank() && !binding.description.text.isNullOrBlank()) {
                createPost()
            }
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> {
                finish()
                return true
            }
        }
        return super.onOptionsItemSelected(item)
    }



    private fun createPost() {
        val post = Post(
            id = "",
            name = binding.title.text.toString(),
            description = binding.description.text.toString(),
        )
        RetrofitClient.apiService.createPost(post).enqueue(object: Callback<Post> {
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
        Toast.makeText(this, "Event created successfully.", Toast.LENGTH_SHORT).show()
    }
}