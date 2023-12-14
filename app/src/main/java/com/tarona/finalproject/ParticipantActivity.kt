package com.tarona.finalproject

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.tarona.finalproject.adapters.PostAdapter
import com.tarona.finalproject.databinding.ActivityParticipantBinding
import com.tarona.finalproject.models.Post
import com.tarona.finalproject.network.RetrofitClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ParticipantActivity : AppCompatActivity() {

    private lateinit var binding: ActivityParticipantBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityParticipantBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)

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

    override fun onResume() {
        super.onResume()
        getPost()
    }

    private fun getPost() {
        val activity = this
        RetrofitClient.apiService.getParticipantList().enqueue(object: Callback<List<Post>> {
            override fun onResponse(call: Call<List<Post>>, response: Response<List<Post>>) {
                if (response.isSuccessful) {
                    val data: List<Post>? = response.body()
                    if(data != null) {
                        binding.participantList.layoutManager = LinearLayoutManager(activity)
                        binding.participantList.adapter = PostAdapter(activity, data)
                        binding.progress.visibility = View.GONE
                    }
                } else {
                    showError()
                }
            }

            override fun onFailure(call: Call<List<Post>>, t: Throwable) {
                showError()
            }
        })
    }

    private fun showError() {
        Toast.makeText(this, "Failed to load data.", Toast.LENGTH_SHORT).show()
    }
}