package com.tarona.finalproject

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import com.tarona.finalproject.databinding.ActivityEventProgressBinding

class EventProgress : AppCompatActivity() {
    private lateinit var binding: ActivityEventProgressBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityEventProgressBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        binding.upcomingBtn.setOnClickListener {
            startActivity(Intent(this, EmptyEvent::class.java))
        }

        binding.ongoingBtn.setOnClickListener {
            startActivity(Intent(this, OngoingEvent::class.java))
        }

        binding.previousBtn.setOnClickListener {
            startActivity(Intent(this, FinishedEvent::class.java))
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
}