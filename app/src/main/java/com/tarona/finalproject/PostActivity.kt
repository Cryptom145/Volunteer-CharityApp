package com.tarona.finalproject

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import com.tarona.finalproject.constants.Constants
import com.tarona.finalproject.databinding.ActivityPostBinding
import com.tarona.finalproject.models.Post
import com.tarona.finalproject.network.RetrofitClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class PostActivity : AppCompatActivity() {
    private lateinit var binding: ActivityPostBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPostBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        binding.checkBtn.setOnClickListener{

            checkParticipants()
        }

        binding.participateBtn.setOnClickListener{

            participateEvent()

        }

        binding.deleteBtn.setOnClickListener{

            deleteEvent()

        }
    }

    override fun onResume() {
        super.onResume()
        getPost()
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

    private fun getPost() {
        val id = intent.getStringExtra(Constants.PARAM_ID) ?: return
        RetrofitClient.apiService.getPostById(id).enqueue(object: Callback<Post> {
            override fun onResponse(call: Call<Post>, response: Response<Post>) {
                if (response.isSuccessful) {
                    val data: Post? = response.body()
                    if(data != null) {
                        binding.description.text = data.description
                        binding.name.text = data.name
                        binding.progress.visibility = View.GONE
                    }
                } else {
                    showError()
                }
            }

            override fun onFailure(call: Call<Post>, t: Throwable) {
                showError()
            }
        })
    }

    private fun participateEvent(){
        binding.participateBtn.setOnClickListener {
            startActivity(Intent(this, EnterEvent::class.java))
        }
    }

    private fun checkParticipants(){
        binding.checkBtn.setOnClickListener {
            startActivity(Intent(this, ParticipantActivity::class.java))
        }
    }

    private fun deleteEvent(){
        val id = intent.getStringExtra(Constants.PARAM_ID) ?: return
        RetrofitClient.apiService.deletePost(id).enqueue(object: Callback<Post> {
            override fun onResponse(call: Call<Post>, response: Response<Post>) {
                if (response.isSuccessful) {
                    finish()
                    successDelete()
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
        Toast.makeText(this, "Failed to load data.", Toast.LENGTH_SHORT).show()
    }

    private fun successDelete() {
        Toast.makeText(this, "Tweet deleted successfully.", Toast.LENGTH_SHORT).show()
    }
}