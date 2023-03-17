package com.yellowh.wego

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.gson.Gson
import com.yellowh.wego.databinding.ActivityMainBinding
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import okhttp3.*
import java.io.IOException

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        navigationBar()

    } // onCreate

    fun navigationBar() {
        binding.bottomNavigationview.setOnItemSelectedListener {
            when (it.itemId) {
                R.id.home -> {
                    println("HomeFragment 수행!!")
                    changeFragment(HomeFragment())
                }
                R.id.user -> {
                    println("UserFragment 수행!!")
                    changeFragment(UserFragment())
                }
            } // when
            true

        } // .setOnItemSelectedListener
    } // navigationBar

    fun changeFragment(fragment: Fragment) {
        supportFragmentManager.beginTransaction()
            .replace(binding.fragmentFrame.id, fragment).commit()
    } // changeFragment

} // end class