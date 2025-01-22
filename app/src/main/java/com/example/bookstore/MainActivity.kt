package com.example.bookstore

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import com.example.bookstore.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        supportActionBar?.hide()

        val books = mutableListOf(
            Book("One Hundred Years of Solitude", "Gabriel García ", R.drawable.solitude, 4f),
            Book("Terra Nostra", "Carlos Fuentes", R.drawable.nostra, 5f),
            Book("Angels & Demons", "Dan Brown", R.drawable.angels, 3f),
            Book("The Sword Thief", "Peter Lerangis", R.drawable.sword, 5f),
            Book("Inferno", "Dan Brown", R.drawable.spirits, 4.5f),
            Book("Bloodline", "James Rollins", R.drawable.blood, 3.5f)
        )
        binding.recyclerView.layoutManager = LinearLayoutManager(this)
        binding.recyclerView.adapter = BookAdapter(books)

        binding.menuIcon.setOnClickListener {
            binding.drawerLayout.openDrawer(androidx.core.view.GravityCompat.START)
        }

        binding.notificationIcon.setOnClickListener {
            Toast.makeText(this, "Notification icon clicked", Toast.LENGTH_SHORT).show()
        }

        binding.navigationView.setNavigationItemSelectedListener { menuItem ->
            val itemName = menuItem.title
            Toast.makeText(this, "$itemName clicked", Toast.LENGTH_SHORT).show()
            binding.drawerLayout.closeDrawers()
            true
        }
    }
}