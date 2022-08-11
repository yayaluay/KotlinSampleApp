package com.example.kotlinsampleapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.kotlinsampleapp.fragment.ListFragment
import com.example.kotlinsampleapp.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // ユーザリストFragment呼び出す
        val fragment = ListFragment()
        val fragmentManager = supportFragmentManager
        val fragmentTransaction = fragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.content, fragment)
        fragmentTransaction.commit()
    }
}