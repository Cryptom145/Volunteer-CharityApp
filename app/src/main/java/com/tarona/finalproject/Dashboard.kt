package com.tarona.finalproject

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import com.tarona.finalproject.databinding.ActivityDashboardBinding

class Dashboard : AppCompatActivity() {
    private lateinit var binding: ActivityDashboardBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDashboardBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        binding.addEvent.setOnClickListener {
            startActivity(Intent(this, CreatePostActivity::class.java))
        }

        binding.searchEvent.setOnClickListener {
            startActivity(Intent(this, ListActivity::class.java))
        }

        binding.aboutUs.setOnClickListener {
            startActivity(Intent(this, AboutUs::class.java))
        }

        binding.progressEvent.setOnClickListener {
            startActivity(Intent(this, EventProgress::class.java))
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